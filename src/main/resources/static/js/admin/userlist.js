// 검색 이벤트
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

// 유저 상세정보
function openPersonModal(uno) {
    fetch(`/user/view/${uno}`)
        .then(response => response.json())
        .then(user => {
            // 모달 내용 갱신
            document.getElementById('userUno').textContent = user.uno;
            document.getElementById('userName').textContent = user.name;
            document.getElementById('userId').textContent = user.userid;
            document.getElementById('userPhone').textContent = user.phone;
            document.getElementById('userEmail').textContent = user.email;
            document.getElementById('userDoptApply').textContent = user.dopt_apply;
            document.getElementById('userRegdate').textContent = user.regdate;
            document.getElementById('userAddr').textContent = user.addr;
            document.getElementById('userDetailAddr').textContent = user.detailaddr;

            // 모달 창 띄우기
            const modal = new bootstrap.Modal(document.getElementById('userInfoModal'));
            modal.show();
        })
}


