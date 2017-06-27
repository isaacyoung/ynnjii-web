/**
 * Created by Administrator on 2017/6/20.
 */
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
    fileInputs.push(document.getElementById("fileImg3"));
    fileInputs.push(document.getElementById("fileImg4"));
    fileInputs.push(document.getElementById("fileImg5"));
    fileInputs.push(document.getElementById("fileImg6"));
    fileInputs.push(document.getElementById("fileImg7"));
    fileInputs.push(document.getElementById("fileImg8"));
    var avatarImgs = [];
    avatarImgs.push(document.getElementById("avatar3"));
    avatarImgs.push(document.getElementById("avatar4"));
    avatarImgs.push(document.getElementById("avatar5"));
    avatarImgs.push(document.getElementById("avatar6"));
    avatarImgs.push(document.getElementById("avatar7"));
    avatarImgs.push(document.getElementById("avatar8"));

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
    fileInputs[2].addEventListener("change", function (event) {
        var file = this.files[0];
        var fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = function (event) {
            var avatarImg = avatarImgs[2];
            avatarImg.src = this.result;
            ajaxSaveToServer(file);
        }
    }, false);
    fileInputs[3].addEventListener("change", function (event) {
        var file = this.files[0];
        var fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = function (event) {
            var avatarImg = avatarImgs[3];
            avatarImg.src = this.result;
            ajaxSaveToServer(file);
        }
    }, false);
    fileInputs[4].addEventListener("change", function (event) {
        var file = this.files[0];
        var fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = function (event) {
            var avatarImg = avatarImgs[4];
            avatarImg.src = this.result;
            ajaxSaveToServer(file);
        }
    }, false);
    fileInputs[5].addEventListener("change", function (event) {
        var file = this.files[0];
        var fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = function (event) {
            var avatarImg = avatarImgs[5];
            avatarImg.src = this.result;
            ajaxSaveToServer(file);
        }
    }, false);

};