
console.log("admin console")
document.querySelector("#image_input_field").addEventListener("change", function(event){

    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function(event) {
        document.querySelector("#upload_image_preview").setAttribute("src", reader.result);
    };
    reader.readAsDataURL(file);
})