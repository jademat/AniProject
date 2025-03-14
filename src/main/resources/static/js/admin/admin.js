function toggleMenu(menuId) {
    let submenus = document.querySelectorAll(".submenu");
    submenus.forEach(menu => {
        if (menu.id !== menuId) {
            menu.style.display = "none";
        }
    });

    let menu = document.getElementById(menuId);
    menu.style.display = (menu.style.display === "block") ? "none" : "block";
}

const loginfrm = document.querySelector("#loginfrm")

loginfrm.addEventListener("submit", (e) => {
    e.preventDefault(); // submit 버튼 기능 중지

    clearErrorMessages();   // 에러메세지 초기화
    // 입력 요소 유효성 검사
    let isValid = validLogin(e.target);

    if (isValid) submitLoginfrm(e.target);
});

function clearErrorMessages() {
    document.querySelectorAll(".error-message")
        .forEach(error => error.textContent = '');
}

const displayErrorMessages = (input, message) => {
    let error = document.createElement('div');
    error.className = 'error-message';
    error.textContent = message;
    input.parentElement.appendChild(error);
}

const loginMessages = [
    '아이디를 올바르게 입력하세요',
    '비밀번호를 올바르게 입력하세요',
];

// 로그인 폼 유효성 검사
const validLogin = (form) => {
    let isValid = true;
    // 로그인 폼안의 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');

    inputs.forEach((input, idx) => {    // input 요소를 하나씩 검사
        if (!input.checkValidity()) { // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input , loginMessages[idx]);
            isValid = false;
        }
    });

    return isValid;
}


const submitLoginfrm = async (frm) => {
    /*console.log(frm.passwd.value);*/

    // 폼에 입력된 데이터를 formData 객체로 초기화
    const formData = new FormData(frm);

    fetch('/admin/login', {
        method: 'POST',
        body: formData
    }).then(async response => {
        if (response.ok) { // 로그인 성공했다면
            alert('로그인 성공');
            location.href = '/home/main';
        } else if (response.status === 400) {
            alert(await response.text());
        } else { // 로그인 실패
            alert('로그인 실패');

        }
    }).catch(error => {
        console.error('login error', error);
        alert('서버와 통신중 오류 발생');
    });
}