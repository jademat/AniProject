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

let findbtn = document.querySelector("#findbtn");
let findtype = document.querySelector("#findtype")
let findkey = document.querySelector("#findkey")

window.addEventListener('load', function () {
    let storedFindType = sessionStorage.getItem('findtype');
    if (storedFindType) {
        findtype.value = storedFindType; // 저장된 값으로 설정
    }
});

findbtn.addEventListener('click', (e) => {
    if(findkey.value === ''){
        alert('검색어를 입력해주세요.');
    } else{
        sessionStorage.setItem('findtype', findtype.value);
        let params = `findtype=${findtype.value}&findkey=${findkey.value}`;
        location.href = `/user/find?${params}`;
    }
});