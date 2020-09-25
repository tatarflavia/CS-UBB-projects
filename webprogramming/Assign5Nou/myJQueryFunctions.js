$(document).ready(function(){

    var $body=$('body');
    var currentFirstSlide=1;
    var $slider=$(".slides");
    var slideCount=$slider.children().length;

    var animationTime=10000;

    var mySlidingTimer;
    startSliding();

    $slider.css("margin-left", "-3140px");
    function slidingImages() {
        $slider.animate({
            marginLeft:'+=1588px',
        },animationTime,function () {
            currentFirstSlide++;
            if(currentFirstSlide===slideCount-1)
            {
                currentFirstSlide=1;
                $(this).css("margin-left","-3140px");
            }
        });
    }

    $body.on('click','img',function(){
        stopSliding();
        $(".popUp").css("display","block");
        $('.popUpImage').attr('src',$(this).attr('src'));
    });

    $body.on('click', '.popUpImage', function () {
        $(".popUp").css("display","none");
        startSliding();
    });



    function stopSliding(){
        $slider.stop(true);
        clearInterval(mySlidingTimer);
    }

    function startSliding() {
        mySlidingTimer=setInterval(slidingImages,10);
    }






























});