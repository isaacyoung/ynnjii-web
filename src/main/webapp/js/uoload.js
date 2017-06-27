function ajaxSaveToServer(imgFile) {
    var xhr = new XMLHttpRequest();
    xhr.open("post", "saveImg", true);
    var formData = new FormData();
    formData.append("img", imgFile);
    xhr.send(formData);
    xhr.onreadystatechange = function () {
        var state = xhr.readyState;
        var status = xhr.status;
        if (state == 4 && status == 200) {
            alert(xhr.responseText);
        }
    }
}
window.onload = function () {
    var fileInputs = [];
    fileInputs.push(document.getElementById("fileImg1"));
    fileInputs.push(document.getElementById("fileImg2"));
    var avatarImgs = [];
    avatarImgs.push(document.getElementById("avatar1"));
    avatarImgs.push(document.getElementById("avatar2"));
    fileInputs[0].addEventListener("change", function (event) {
        var file = this.files[0];
        var fileSize = (file.size / 1024).toFixed();
        var fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = function (event) {
            var avatarImg = avatarImgs[0];
            avatarImg.src = this.result;
            ajaxSaveToServer(file);
        }
    }, false);
    fileInputs[1].addEventListener("change", function (event) {
        var file = this.files[0];
        var fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = function (event) {
            var avatarImg = avatarImgs[1];
            avatarImg.src = this.result;
            ajaxSaveToServer(file);
        }
    }, false);

};