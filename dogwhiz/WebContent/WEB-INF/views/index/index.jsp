<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>

    <!-- stylesheets -->
    <link rel="stylesheet" href="./css/indexCSS.css">
    <link rel="stylesheet" href="./css/mapCSS.css">

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" defer></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js" defer></script>

    <!-- Tmap API-->
    <script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=n2IwI2iBAAaPo1CrlNRhm97bE9BUPkzy5C7L9K9C"></script>
    <script src="./js/map/map.js" defer></script>
    <script src="./js/map/mapmenu.js" defer></script>
    <script src="./js/map/mapsearch.js" defer></script>

    <!-- 날씨 -->
    <script src="./js/map/weather.js" defer></script>
    
    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cute+Font:400" rel="stylesheet">

    <!-- Fontawesome 아이콘 -->
    <script src="https://kit.fontawesome.com/8382ce3f5f.js" crossorigin="anonymous"></script>

    <title>도그위즈</title>

</head>

<body onload="initTmap()">
    <section class="pc">
        <div class="pc_main">
        </div>
    </section>

    <section class="mobile">
        <div class="pc_logo">
            <a href="./">
                <img src="./images/logo/logo.png">
            </a>
        </div>
        <div class="container section">
            <div class="card_container">
                <div class="card">
                    <div class="container"></div>
                    <div class="text_cont">
                        <h2>강아지 예방접종!</h2>
                        <p>강아지 예방접종의 모든것! 너무도 소중한 우리 강아지를 위한 접종 종류와 시기를 알아보세요.</p>
                    </div>
                    <div class="more">자세히 보기</div>
                </div>
                <div class="card">
                    <div class="container"></div>
                    <div class="text_cont">
                        <h2>강아지 산책 Tip!</h2>
                        <p>강아지와 산책할 때 필수 준비물! 매너! 교육방법 등을 확인해보세요.</p>
                    </div>
                    <div class="more">자세히 보기</div>
                </div>
                <div class="card">
                    <div class="container"></div>
                    <div class="text_cont">
                        <h2>개인기 훈련!</h2>
                        <p>너무 귀여운 우리 강아지의 개인기 훈련 팁을 알아보세요.</p>
                    </div>
                    <div class="more">자세히 보기</div>
                </div>
            </div>
        </div>
        <iframe src="main" width="100%" height="100%" class="m_main">
        </iframe>
    </section>

    <section class="map">
        <section class="map_section">
            <div class="map_header">
                <div class="nowLocation_box">
                    <span>현재위치 : </span>
                    <span class="nowLocation_text">asdsads</span>
                </div>
                <div class="weather_box">
                    <div>
                            <div class="temp_min"></div>
                            <div class="temp_max"></div>
                    </div>
                    <div>
                            <div class="humidity"></div>
                            <div class="wind"></div>
                            <div class="cloud"></div>
                    </div>
                    <div>
                        <div class="weather_icon"></div>
                    </div>
                    <div>
                        <div class="current_temp"></div>
                        <div class="weather_description"></div>
                        <div class="city"></div>
                    </div>
                </div>
            </div>

            <div class="map_warp">
                <div class="map_menubar">
                    <ul class="map_menubar_tools">
                        <li class="map_menubar_tools_btns" id="tools_nowlocation"><i
                                class="fa-solid fa-location-crosshairs"></i><span>현재위치</span></li>
                        <li class="map_menubar_tools_btns" id="tools_marker"><i
                                class="fa-solid fa-location-dot"></i><span>마커</span></li>
                        <li class="map_menubar_tools_btns" id="tools_pet_marker"><i
                                class="fa-solid fa-paw"></i><span>펫마커</span></li>
                        <li class="map_menubar_tools_btns" id="tools_draw_line"><i
                                class="fa-solid fa-route"></i><span>경로그리기</span></li>
                        <li class="map_menubar_tools_btns" id="tools_draw_polygon"><i
                                class="fa-solid fa-draw-polygon"></i><span>영역그리기</span></li>
                        <li class="map_menubar_tools_btns" id="tools_reset"><i
                                class="fa-solid fa-rotate-left"></i><span>지도리셋</span></li>
                    </ul>
                </div>

                <div class="map_showMap">
                    <ul class="map_showMap_side_marker">
                        <li id="starting_marker"><img src="image/map/start.png" style="height:100%"></li>
                        <li id="waypoint_marker"><img src="image/map/waypoint1.png" style="height:100%"></li>
                        <li id="destination_marker"><img src="image/map/destination.png" style="height:100%"></li>
                    </ul>

                    <ul class="map_showMap_side_petmarker">
                        <li><img src="image/map/poop.png" style="height:100%"></li>
                        <li><img src="image/map/danger.png" style="height:100%"></li>
                        <li><img src="image/map/camera.png" style="height:100%"></li>
                        <li><img src="image/map/here.png" style="height: 100%"></li>
                    </ul>

                    <div id="map_div"></div>
                    <p id="result"></p>
                </div>
            </div>

            <div class="main_homepage">
            </div>


            <div class="map_searchbar">
                <div class="map_searchbar_top">
                    <a>지명검색</a>
                    <input class="inputplace" type="text" value="">
                    <button type="button" class="map_searchbar_searchBtn">검색</button>
                </div>
                <ul class="quicksearch">
                    <li><a href="#">동물병원</a></li>
                    <li class="quicksearch_line"><a href="#">펫샵</a></li>
                    <li class="quicksearch_line"><a href="#">카페</a></li>
                    <li class="quicksearch_line"><a href="#">호텔</a></li>
                </ul>
                <div class="directions_search">
                    <div class="search_box">
                        <div>
                            <label>출발지</label>
                            <input class="startPointText" type="text" value="출발지" readonly>
                        </div>
                        <div class="waypoint" id="waypoint1">
                            <label>경유지</label>
                            <input class="waypointText1" type="text" class="draggable-textbox" value="경유지1" readonly>
                            <span id="delete_waypoint1"><img src="image/map/minus.png" height="20px"></span>
                        </div>
                        <div class="waypoint" id="waypoint2">
                            <label>경유지</label>
                            <input class="waypointText2" type="text" class="draggable-textbox" value="경유지2" readonly>
                            <span id="delete_waypoint2"><img src="image/map/minus.png" height="20px"></span>
                        </div>
                        <div class="waypoint" id="waypoint3">
                            <label>경유지</label>
                            <input class="waypointText3" type="text" class="draggable-textbox" value="경유지3" readonly>
                            <span id="delete_waypoint3"><img src="image/map/minus.png" height="20px"></span>
                        </div>
                        <div class="waypoint" id="waypoint4">
                            <label>경유지</label>
                            <input class="waypointText4" type="text" class="draggable-textbox" value="경유지4" readonly>
                            <span id="delete_waypoint4"><img src="image/map/minus.png" height="20px"></span>
                        </div>
                        <div class="waypoint" id="waypoint5">
                            <label>경유지</label>
                            <input class="waypointText5" type="text" class="draggable-textbox" value="경유지5" readonly>
                            <span id="delete_waypoint5"><img src="image/map/minus.png" height="20px"></span>
                        </div>
                        <div>
                            <label>도착지</label>
                            <input class="destinationText" type="text" value="도착지" readonly>
                        </div>
                    </div>
                    <div class="search_route_btns">
                        <button class="route_allreset" type="button">전체삭제</button>
                        <button class="route_search" type="button">길찾기</button>
                    </div>
                </div>

                <div class="search_result">
                    <ul>
                        <li>검색결과1</li>
                        <li>검색결과2</li>
                    </ul>
                </div>
            </div>

            <button id="btn_menu">
                <img src="image/map/right.png" alt="" style="font-weight:100%">
            </button>
        </section>
</body>

</html>