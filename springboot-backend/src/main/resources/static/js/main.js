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


function build_button_html(src1,src2){
    var html = '<div class="col col2 align-right col-md-5 col-sm-5 zero-left-padding uppercase" style="margin-bottom:-5px;top:-5px"><a id="tools-share-fb" href="#" class="zero-right-margin"><img src="'+
    src1 + '"/></a>&nbsp;<a id="tools-share-ln-popup" href="#" class="zero-right-margin"><img src="'+ src2 + '"/></a></div>';
    return html;
}

var prefix = 'hackathon';
if (hack_prefix) {
    prefix = hack_prefix;
}

var root = 'http://54.171.123.75/' + prefix
var static_root = root +"/static/"

var tools_view = $('#tools-view');

var html_fb_button = build_button_html(static_root+"FB.png",static_root+"ln.png");
tools_view.append( html_fb_button );

//var html_ln_button_popup =  build_button_html(static_root+"/ln.png");
//tools_view.append(html_ln_button_popup)

// Shift toolbar

first = tools_view.children().first();
first.toggleClass("col9");
first.toggleClass("col7");


//load linked in sdk
add_ln_script()
//add ln sign in button
var ln_login_html = '<script type="in/Login"><?js= firstName ?> <?js= lastName ?> <input type="button" value="Logout !" onclick="logout()"></script>'
//tools_view.append(ln_login_html)

var html_ln_button = '<div class="col col1 align-right col-md-5 col-sm-5 zero-left-padding uppercase"><a id="tools-share-ln" href="#" class="zero-right-margin"><img src="https://www.facebook.com/rsrc.php/v3/yQ/r/7GFXgco-uzw.png"><i class="icon"/></a></div>';
//tools_view.append(html_ln_button)




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

console.log(document.getElementById('tools-share-ln-popup'))

document.getElementById('tools-share-ln-popup').onclick = function() {

    var publication_content = get_publication_content();
    var publication_content_base64 = btoa(JSON.stringify(publication_content));

    var url = "https://www.linkedin.com/shareArticle?"

    cb_url = 'http://54.171.123.75/' + prefix + '/share/fb/' + publication_content_base64,
    url = url +"url=" + cb_url;

    window.open(encodeURI(url),'linkedinwindow','left=20,top=20,width=600,height=700,toolbar=0,resizable=1');

}
