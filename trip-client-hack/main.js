// Insert Facebook SDK

window.fbAsyncInit = function() {
    FB.init({
        appId      : '1823888207866789',
        xfbml      : true,
        version    : 'v2.8'
    });

    FB.AppEvents.logPageView();
};

(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Insert Button

var tools_view = $('#tools-view');
var html_fb_button = '<div class="col col1 align-right col-md-5 col-sm-5 zero-left-padding uppercase"><a id="tools-share-fb" href="#" class="zero-right-margin"><img src="https://www.facebook.com/rsrc.php/v3/yQ/r/7GFXgco-uzw.png"><i class="icon"/>&nbsp;Share</a></div>';
tools_view.append( html_fb_button );

// Shift toolbar

first = tools_view.children().first();
first.toggleClass("col9");
first.toggleClass("col8");

// Add click handler

document.getElementById('tools-share-fb').onclick = function() {

    var flight_items = EG.tripResponse.items.filter(function(item) {
        return item.item_type === 'FLIGHT';
    });

    var destination = flight_items[0].origin_destinations[0].arrival_location.city;
    var date = EG.tripResponse.start_datetime;

    var publication_content = {
        'date': date,
        'destination': destination
    };

    var publication_content_base64 = btoa(JSON.stringify(publication_content));

    FB.ui({
        method: 'share',
        display: 'popup',
        hashtag: '#egencia',
        href: 'http://radiant-garden-1387.herokuapp.com/11',
    }, function(response){});
    return false;
}