"use strict";

$(function () {
  $("#searchZipCodeBtn").on("click", function () {
    $.ajax({
      url: "https://zipcoda.net/api",
      type: "GET",
      dataType: "json",
      data: {
        zipcode: $("#zipcode").val(),
      },
      async: true,
    })
      .done(function (data) {
        let pref = data.items[0].components[0];
        let municipalities = data.items[0].components[1];
        let address = data.items[0].components[2];

        $("#pref").val(pref);
        $("#municipalities").val(municipalities);
        $("#address").val(address);
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });
});
