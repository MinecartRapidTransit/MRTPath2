require "yaml"
module Network
  class StationsEval
    %w(north south east west).each do |method|
      define_method method do |name, *args|
        @stations ||= []
        @stations << [name, method, args[0] || 0, args[1] || (args[0] || 0)]
      end
    end

    def eval(&block)
      instance_eval &block
      @stations || []
    end
  end

  class LineEval

    def method_missing(method, *args, &block)
      @stuff[method] = args[0]
    end

    def initialize(name)
      @stuff = {name: name}
      @stuff[:station_formatter] = lambda do |name, station|
        "#{name} #{station}"
      end
      @stuff[:id_formatter] = lambda do |code, count|
        "#{code}#{count}"
      end
    end

    def id_formatter(&block)
      @stuff[:id_formatter] = block
    end

    def station_formatter(&block)
      @stuff[:station_formatter] = block
    end

    def stations(&block)
      @stuff[:stations] = StationsEval.new.eval(&block)
    end

    def eval(&block)
      instance_eval(&block)
      @stuff
    end

  end

  class PlaceEval
    def method_missing(method, *args, &block)
      @places ||= {}
      @places[method] = (args[0] || method.to_s.gsub("_", " ").split.map(&:capitalize)*' ')
    end

    def eval(&block)
      instance_eval(&block)
      @places || {}
    end
  end

  class Creator
    def places(&block)
      places = PlaceEval.new.eval &block
      @places ||= {}
      @places = @places.merge places
    end

    def line(name, &block)
      @lines ||= []
      @lines << LineEval.new(name).eval(&block)
    end

    def eval(name, &block)
      @name = name
      instance_eval &block
      output = ""
      output += "# Network: #{@name}\n" if @name
      output += "# Generated by network_creator.rb on #{Time.now}\n"
      # 1: Go through all the stations getting places
      @lines.each do |line|
        line[:stations].each do |station|
          @places[station[0]] = station[0].to_s.gsub("_", " ").split.map(&:capitalize)*' ' unless @places[station[0]]
        end
      end
      # 2: Create the yaml hash
      yaml={'places'=>@places.to_a.map{|k,v|[k.to_s,{'name'=>v,'stations'=>[]}]}.to_h} # Probably could do w/ whitespace
      # 3: Go through each line making stations and connections
      @lines.each do |line|
        counter = 0
	prevstation = nil
        line[:stations].each do |station|
          counter += 1
          shash = {
            'id' => line[:id_formatter].call(line[:code], counter),
            'name' => line[:station_formatter].call(line[:name], @places[station[0]]),
            'connections' => []
          }
          shash['connections'] << {
            'name' => "#{line[:name]} #{station[1].capitalize}bound",
            'distance' => station[2],
            'to' => line[:id_formatter].call(line[:code], counter + 1),
            'type' => line[:type]
          } if counter < line[:stations].length
          station[1] = case station[1]
          when "north" then "south"
          when "south" then "north"
          when "east" then "west"
          when "west" then "east"
          end
          shash['connections'] << {
            'name' => "#{line[:name]} #{station[1].capitalize}bound",
            'distance' => prevstation[3],
            'to' => line[:id_formatter].call(line[:code], counter - 1),
            'type' => line[:type]
          } if counter > 1
          yaml['places'][station[0].to_s]['stations'] << shash
          prevstation = station
        end
      end
      # That's it. Print, profit
      puts YAML.dump yaml
    end

    def self.go(name = "", &block)
      self.new.eval(name, &block)
    end
  end
end

