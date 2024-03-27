var img = document.getElementById("image");

function photoSwitch(){
    img.setAttribute("class", "some-animation");
    setTimeout(function(){
        img.src = "";
    },2000);
}

photoSwitch();