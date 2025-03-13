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
            document.getElementById('userRegdate').textContent = user.regdate;
            document.getElementById('userAddr').textContent = user.addr;
            document.getElementById('userDetailAddr').textContent = user.detailaddr;



            // 모달 창 띄우기
            const modal = new bootstrap.Modal(document.getElementById('userInfoModal'));

            modal.show();
        })
}
function confirmDelete(idx) {
    if (confirm("정말 삭제하시겠습니까?")) {
        // DELETE 요청 보내기
        let form = document.getElementById(`deleteForm${idx}`);
        fetch(form.action, {
            method: 'Post', // 실제 DELETE 요청을 보냄
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    alert("유저가 삭제되었습니다.")
                    window.location.href = '/user/userlist';  // 삭제 후 목록 페이지로 리디렉션
                } else {
                    alert('삭제에 실패했습니다.');
                }
            })
            .catch(error => alert('삭제 중 오류가 발생했습니다.'));
    }
}

