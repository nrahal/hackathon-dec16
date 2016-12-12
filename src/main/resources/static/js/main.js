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

var html_fb_button = '<div class="col col1 align-right col-md-5 col-sm-5 zero-left-padding uppercase"><a id="tools-share-fb" href="#" class="zero-right-margin" data-control="modal" data-modal-id="receiptModal"><i class="icon icon-file"/>&nbsp;Share</a></div>';
$('#tools-view').append( html_fb_button );




document.getElementById('tools-share-fb').onclick = function() {
    FB.ui({
        method: 'share',
        display: 'popup',
        hashtag: '#egencia',
        href: 'http://radiant-garden-1387.herokuapp.com/11',
    }, function(response){});
    return false;
}