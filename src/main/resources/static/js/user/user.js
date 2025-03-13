const errorMessages = [
    '※ 아이디는 소문자로 시작하고, 영문자와 숫자만 사용 가능합니다 (최소 6자 ~ 최대 18자)',
    '※ 비밀번호는 영문 대소문자, 숫자, 특수문자를 포함해야 합니다 (최소 6자 ~ 최대 18자)',
    '※ 비밀번호가 일치하지 않습니다',
    '※ 이름은 한글 또는 영문자만 사용 가능합니다',
    '※ 전화번호를 입력해주세요(예: 010-1234-5678)',
    '※ 주소 검색을 해주세요',
    '※ 상세 주소를 입력해주세요',
    '※ 올바른 이메일 형식이 아닙니다',
    '※ 자동가입방지를 확인하세요'];

const loginMessages = [
    '※ 아이디를 올바르게 입력하세요',
    '※ 비밀번호를 올바르게 입력하세요',
];

const editMessages = [
    '※ 비밀번호는 영문 대소문자, 숫자, 특수문자를 포함해야 합니다 (최소 6자 ~ 최대 18자)',
    '※ 비밀번호가 일치하지 않습니다',
]

const patterns = [
    /^[a-z][a-z0-9]{5,17}$/,
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,18}$/,
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,18}$/,
    /^[가-힣]{2,5}|[a-zA-Z]{2,10}$/,
    /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/,
    /^[\w\s,.-]{5,150}$/,
    /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
];

// 모든 error-message 요소의 내용을 초기화

function clearErrorsMessages() {
    document.querySelectorAll(".error-message")
        .forEach(error => error.textContent='');
}


// 입력 요소 유효성 검사
const validInputs = (form) => {
    let isValid = true;

    // 먼저 기존의 에러 메시지를 모두 초기화
    clearErrorsMessages();

    // 회원가입 폼안의 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');
    inputs.forEach((input, idx) => {    // input 요소에 인덱스를 줘서 하나씩 검사
        if (!input.checkValidity()) {   // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input, errorMessages[idx]);
            isValid = false;
        }
    });
    // 비밀번호 일치 여부 검사
    if (inputs[1].value !== inputs[2].value) {
        displayErrorMessages(inputs[2], errorMessages[2]);
        isValid = false;
    }
    // 전화번호 유효성 검사 추가
    const phone = inputs[4].value;  // 전화번호 입력칸
    if (!/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/.test(phone)) {
        displayErrorMessages(inputs[4], errorMessages[4]);  // 전화번호 형식이 맞지 않으면 에러 메시지 표시
        isValid = false;
    }

    // 주소 입력 여부 검사 추가
    const addr = inputs[5].value;  // 주소 입력칸
    if (!addr) {
        displayErrorMessages(inputs[5], errorMessages[5]);  // 주소 입력이 없으면 에러 메시지 표시
        isValid = false;
    }


    return isValid;
}

// 오류메세지 출력
const displayErrorMessages = (input, message) => {
    let error = document.createElement('div');
    error.className='error-message';
    error.textContent = message;
    input.parentElement.appendChild(error);

    // 부모 요소에 에러 메시지를 추가
    if (input.id === 'addr') {
        // 주소 입력 필드의 경우, 주소 입력란 바로 아래에 메시지를 표시
        input.parentElement.parentElement.appendChild(error);  // 부모의 부모에 추가 (주소 검색 버튼이 있는 부모의 부모)
    } else {
        // 나머지 필드들은 그냥 부모 요소에 추가
        input.parentElement.appendChild(error);
    }
}

// 비밀번호 해싱
// async와 await를 사용해야 하는 이유는 hashPassword 함수가 비동기적으로 동작하기 때문
// 비동기 함수는 일반적으로 네트워크 요청, 파일 I/O, 암호화 작업 등 시간이 오래 걸리는 작업을 수행할 때 사용
// 이러한 작업은 완료될 때까지 기다려야 하며, 이때 async와 await를 사용하여 코드의 가독성을 높이고 동기적인 코드처럼 작성할 수 있음
const hashPassword = async (passwd) => {
    // 문자열을 Uint8Array로 변환
    const encoder = new TextEncoder();  // TextEncoder 객체 생성
    const data = encoder.encode(passwd); // 비밀번호를 Uint8Array로 변환

    // SHA-256 해시 생성
    // crypto.subtle.digest는 비동기 함수이므로 await 또는 .then()을 사용하여 결과를 처리해야 함
    // SHA-256은 암호학적으로 안전한 해시 함수이지만, 비밀번호 해시에는 추가적으로 솔트(salt)와 키 스트레칭(key stretching)을 사용하는 것이 좋음
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);

    // 해시 결과를 16진수 문자열로 변환
    const hashArray = Array.from(new Uint8Array(hashBuffer)); // Uint8Array를 배열로 변환
    const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join(''); // 16진수 문자열로 변환

    return hashHex;
}

// submit vs fetch
// 폼 submit 방식: 페이지 새로고침 발생, 에러처리는 브라우저에 의존
// fetch 방식: 비동기 처리 가능, 에러처리는 세밀하게 조절 가능(자바스크립트로),
//            요청데이터를 JSON으로 전송 가능, REST API/SPA에서 주로 사용

// 프라미스 (promise)
// 자바스크립트에서 비동기 작업을 처리하기 위한 객체
// 주로 비동기 작업의 성공/실패를 관리하고 결과를 처리하는데 사용
// ES6에서 처음 도입 - 콜백 지옥 해결, 비동기 처리 코드를 깔끔하게 작성
// async : 비동기 함수임을 선언 - 반환값은 프라미스임
// await : 비동기 함수의 처리가 완료될 때까지 기다렸다가 결과를 받아옴
const submitJoinFrm = async (frm) => {
    // Web Crypto API로 비밀번호 암호화
    frm.userpwd.value = await hashPassword(frm.userpwd.value);
    console.log(frm.userpwd.value);



    // 폼에 입력된 데이터를 formData 객체로 초기화
    const formData = new FormData(frm);

    fetch('/user/user/join', {
        method: 'POST',
        body: formData
    }).then(async response => {
        if (response.ok) {  // 회원가입이 정상적으로 처리되었다면
            alert('회원가입이 완료되었습니다!!');
            location.href = 'login';
        } else if (response.status === 400) {
            alert(await response.text());
        }else {     // 회원가입이 실패했다면
            alert('회원가입에 실패했습니다!! 다시 시도 해주세요!');
        }

    }) .catch(error => {
        console.error('join error:', error);
        alert('서버와 통신중 오류가 발생했습니다!! 관리자에게 문의하세요!');
    });
} // submitJoinFrm

const submitEditFrm = async (frm) => {
    // 유효성 검사
    if (!validEditInputs(frm)) {
        return; // 유효성 검사 실패 시 함수 종료
    }

    // Web Crypto API로 비밀번호 암호화
    frm.userpwd.value = await hashPassword(frm.userpwd.value);

    // 폼에 입력된 데이터를 formData 객체로 초기화
    const formData = new FormData(frm);

    fetch('/user/user/edit', {
        method: 'POST',
        body: formData
    }).then(async response => {
        if (response.ok) {  // 회원정보 수정 정상적으로 처리되었다면
            alert('변경이 완료되었습니다!!');
            location.href = 'myinfo';
        } else if (response.status === 400) {
            alert(await response.text());
        }else {     // 회원정보 수정이 실패했다면
            alert('회원정보 변경 실패했습니다!! 다시 시도 해주세요!');
        }

    }) .catch(error => {
        console.error('edit error:', error);
        alert('서버와 통신중 오류가 발생했습니다!! 관리자에게 문의하세요!');
    });
} // submiteditFrm


// 입력 요소 유효성 검사
const validEditInputs = (form) => {
    let isValid = true;
    let hasChanges = false;

    // 먼저 기존의 에러 메시지를 모두 초기화
    clearErrorsMessages();

    // 회원정보 수정 폼안의 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');

    /// 아이디, 이름 제외한 필드만 유효성 검사
    const newPassword = inputs[2]; // 비밀번호 필드
    const confirmPassword = inputs[3]; // 비밀번호 확인 필드
    const phone = inputs[4]; // 전화번호
    const email = inputs[5]; // 이메일
    const addr = inputs[6]; // 주소
    const detailaddr = inputs[7]; // 상세주소

    // 비밀번호가 변경된 경우에만 유효성 검사
    if (newPassword.value.trim() !== "") {
        // 비밀번호 유효성 검사 (6자 이상, 영문 대소문자, 숫자, 특수문자 포함)
        if (!patterns[1].test(newPassword.value)) {
            displayErrorMessages(newPassword, editMessages[0]); // 비밀번호 형식 오류 메시지
            isValid = false;
        }

        // 비밀번호 일치 여부 검사
        if (newPassword.value !== confirmPassword.value) {
            displayErrorMessages(confirmPassword, editMessages[1]); // 비밀번호 불일치 메시지
            isValid = false;
        }
    }

    // 전화번호 유효성 검사
    if (!patterns[4].test(phone.value)) {
        displayErrorMessages(phone, errorMessages[4]);
        isValid = false;
    }

    // 이메일 입력 여부 검사
    if (!patterns[6].test(email.value)) {
        displayErrorMessages(email, errorMessages[7]);
        isValid = false;
    }

    // 주소 입력 여부 검사
    if (!addr.value) {
        displayErrorMessages(addr, errorMessages[5]);
        isValid = false;
    }

    // 상세주소 입력 여부 검사
    if (!detailaddr.value) {
        displayErrorMessages(detailaddr, errorMessages[6]);
        isValid = false;
    }

    // 변경 사항이 없으면 알림
    if (!hasChanges) {
        alert('변경사항이 없습니다!');
        isValid = false;  // 변경사항이 없으므로 유효성 검사 통과하지 않음
    }

    return isValid;

}


// 로그인 폼 유효성 검사
const validLogin = (form) => {
    let isValid = true;

    // 로그인 폼안의 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');
    inputs.forEach((input, idx) => {    // input 요소에 인덱스를 줘서 하나씩 검사
        if (!input.checkValidity()) {   // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input, loginMessages[idx]);
            isValid = false;
        }
    });

    return isValid;
}

const submitLoginFrm = async (frm) => {
    frm.userpwd.value = await hashPassword(frm.userpwd.value);
    const formData = new FormData(frm);

    fetch('/user/user/login', {
        method: 'POST',
        body: formData
    }).then(async response => {
        if (response.ok) {
            alert('로그인이 성공했습니다!!');
            location.href = '/';
        } else {
            // 로그인 실패 시 서버에서 전달된 오류 메시지 출력
            const errorText = await response.text();
            alert(errorText);  // "아이디가 존재하지 않습니다." 또는 "비밀번호가 일치하지 않습니다."
            location.href = '/user/user/login';
        }
    }).catch(error => {
        console.error('login error:', error);
        alert('서버와 통신중 오류가 발생했습니다!! 관리자에게 문의하세요!');
    });
}