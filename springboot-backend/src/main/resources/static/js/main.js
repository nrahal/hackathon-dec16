// Insert Facebook SDK

function add_ln_script( ) {
    var script = document.createElement('script');
    script.setAttribute("src","https://platform.linkedin.com/in.js");

    var text = document.createTextNode("api_key: 77c3z2vzb941q1\n");
    script.appendChild(text);
    text = document.createTextNode("authorize: true");
    script.appendChild(text);
    document.head.appendChild(script);
}

function logout() {
    IN.User.logout();
}


window.fbAsyncInit = function() {
    FB.init({
        appId      : '1057334491042769',
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

//load linked in sdk
add_ln_script()
//add ln sign in button
var ln_login_html = '<script type="in/Login">Signed in as <?js= firstName ?> <?js= lastName ?>. <input type="button" value="Logout !" onclick="logout()"></script>'
tools_view.append(ln_login_html)

var html_ln_button = '<div class="col col1 align-right col-md-5 col-sm-5 zero-left-padding uppercase"><a id="tools-share-ln" href="#" class="zero-right-margin"><i class="icon"/>&nbsp;Share</a></div>';
tools_view.append(html_ln_button)


// Add click handler

function get_publication_content(){
    var flight_items = EG.tripResponse.items.filter(function(item) {
            return item.item_type === 'FLIGHT';
        });

    var destination = flight_items[0].origin_destinations[0].arrival_location.city;
    var date = EG.tripResponse.start_datetime;

    var publication_content = {
            'date': date,
            'destination': destination
    };
    return publication_content;

}

document.getElementById('tools-share-fb').onclick = function() {

    var publication_content = get_publication_content();
    var publication_content_base64 = btoa(JSON.stringify(publication_content));

    var prefix = 'hackathon';
    if (hack_prefix) {
        prefix = hack_prefix;
    }

    FB.ui({
        method: 'share',
        display: 'popup',
        hashtag: '#Egencia',
        href: 'http://54.171.123.75/' + prefix + '/share/fb/' + publication_content_base64,
    }, function(response){});
    return false;
}

document.getElementById('tools-share-ln').onclick = function() {

// Handle the successful return from the API call
function onSuccess(data) {
    console.log("SUCCESS : " + data);
}

// Handle an error response from the API call
function onError(error) {
    console.log("ERROR :" + error);
}

if(!IN.User.isAuthorized()) {
    alert("Not authorized !");
    return;
}

var publication_content = get_publication_content();

// Build the JSON payload containing the content to be shared
var payload = {
                "comment": "kjh",
                "content": {
                    "title": "title",
                    "description": publication_content.destination,
                    "submittedUrl": "https://www.egencia.fr/public/fr/fr/",
                    "submittedImageUrl": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CoQBdwAAAH7XMhsi-E6zICdOTWq6RUD3CyZPYOmkkqjquj2E99h3Svlx-Y6z8KUUkLWP8QNSv9tvMjinSRtatHhDcDO5QF1v3ya2KG7EM7kVQMfX30FuVDHOx2tuGT9ie-x6SPSzWOz9l3q-c0FW4_Snt0dWq8haZqxBL8hD3BVKR-woELv7EhAL8YigSynvGcHF3iMCEukAGhQr0WiJXFAyfx26SFWVdDJ2kk4ZLw&key=AIzaSyArPkZ-NEjfjnfY33ztWqo59ULkpuaT2mU"
                },
                "visibility": {
                    "code": "anyone"
                }
 };

IN.API.Raw("/people/~/shares?format=json")
                    .method("POST")
                    .body(JSON.stringify(payload))
                    .result(onSuccess)
                    .error(onError);
}
