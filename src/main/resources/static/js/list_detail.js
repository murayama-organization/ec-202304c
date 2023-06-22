"user-stract";

/**
 * 商品詳細画面の金額を計算します.
 */
$(function () {
  $(".size").on("change", function () {
    total();
  });
  $(".checkbox").on("change", function () {
    total();
  });
  $("#num").on("change", function () {
    total();
  });

  /**
   * 選択したサイズとトッピングの合計金額を計算します.
   */
  function total() {
    let size = $(".size:checked").val();
    let topping_count = $("#topping input:checkbox:checked").length;

    let size_price = 0;
    let topping_price = 0;
    if (size === "M") {
      size_price = change_int($("#priceM").text());
      topping_price = 200 * topping_count;
    } else if (size === "L") {
      size_price = change_int($("#priceL").text());
      topping_price = 300 * topping_count;
    }
    let item_num = $("#num").val();
    let price = (size_price + topping_price) * item_num;
    $("#totalprice").text(price.toLocaleString());
  }

  /**
   * 商品の価格をint型に変換します.
   * @patram 文字列型の商品価格
   */
  function change_int(price) {
    var remove = price.replace(/,/g, "");
    return parseInt(remove);
  }
});
