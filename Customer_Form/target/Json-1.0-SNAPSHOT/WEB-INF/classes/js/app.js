$('#getAll').click(function (){
    $.ajax({
        url : "https://localhost:8080/app/customer",
        method : "GET",
        success : function (resp) {
            console.log("Success :",resp);
            console.log("id :",resp.id);
            console.log("name :",resp.name);
            console.log("address :",resp.address);

        },
        error : function (error) {
            console.log("error: ", error);

        }
    })
});

$('#btnSave').click(function () {
   $.ajax({
       url: "https://localhost:8080/app/customer",
       method: "Post",
       success : function (resp) {
           console.log("Success:" ,resp);
       },
       error : function (error) {
           console.log("error: " ,error);
       }
   })
});