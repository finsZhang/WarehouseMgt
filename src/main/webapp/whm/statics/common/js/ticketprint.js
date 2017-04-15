function GetPageHeight() {
    var nHeight = (document.body.scrollHeight);
    return nHeight;
}

function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
