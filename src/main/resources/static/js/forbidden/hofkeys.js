//屏蔽F12和右键
function click(e) {
    if (document.all) {
        if (event.button == 2 || event.button == 3) {

            oncontextmenu = 'return false';
        }
    }
    if (document.layers) {
        if (e.which == 3) {
            oncontextmenu = 'return false';
            z
        }
    }
}

if (document.layers) {
    document.captureEvents(Event.MOUSEDOWN);
}
document.onmousedown = click;
document.oncontextmenu = new Function("return false;")
//
document.onkeydown = document.onkeyup = document.onkeypress = function () {
    if (window.event.keyCode == 123) {
        window.event.returnValue = false;
        return (false);
    }
}