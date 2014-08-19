require "network_creator"

Network::Creator.go "MRT 1" do
  
  # Only places with special names need this
  places do
    shea_jungle_resort "Shea-Jungle Resort"
    mrt_marina "MRT Marina"
    wishington_pvp "Wishington-PVP"
    skyscraper_arden "Skyscraper-Arden"
  end

  line "Blue Line" do
    code "B"
    type "rail"
    stations do
      south :gund_valley, 262
      south :north_edge, 431
      south :new_delvin, 475
      east :plainscity, 378
      south :woodzy_beach, 606
      east :welcomeville, 314
      south :shea_jungle_resort, 400
      south :sandstone_fair, 427
      south :sublime, 370
      south :mrt_marina, 285
      east :harbourfront_district, 286
      east :government_district, 319
      south :spawn, 213
      south :theatre_district, 244
      south :industrial_district, 476
      south :miningstone, 261
      east :aranstown, 250
      east :aperture, 402
      east :inchmuir, 395
      east :wishington_pvp, 250
      east :skyscraper_arden, 381
      south :shadow_rock, 242
      south :snowtopia, 256
      south :midcity, 405
      south :primerose, 384
      east :marauder_forest, 355
      east :farragrout_greens, 274
      east :nathanhaven, 209
      east :cactusville, 0
    end
  end
end