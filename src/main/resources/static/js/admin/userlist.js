document.addEventListener("DOMContentLoaded", function() {
    let delbtn = document.querySelector("#delbtn");

    if (delbtn) {
        delbtn.addEventListener("click", function (e) {
            if (confirm('정말 삭제하시겠습니까?')) {

                alert('삭제되었습니다.'); // 예시
            }
        });
    }
});