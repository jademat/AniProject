
function openAdoptModal(adono) {
    fetch(`/adopt/view/${adono}`)
        .then(response => response.json())
        .then(adopt => {
            // 데이터를 모달에 채운다
            document.getElementById('adoptNo').textContent = adopt.adono;
            document.getElementById('userId').textContent = adopt.userid;
            document.getElementById('animalName').textContent = adopt.nm;
            document.getElementById('adoRaised').textContent = adopt.ado_raised;
            document.getElementById('adoMembers').textContent = adopt.ado_members;
            document.getElementById('adoHousing').textContent = adopt.ado_housing;
            document.getElementById('adoAllagree').textContent = adopt.ado_allagree;
            document.getElementById('adoReason').textContent = adopt.ado_reason;
            document.getElementById('adoCost').textContent = adopt.ado_cost;
            document.getElementById('adoSource').textContent = adopt.ado_source;
            document.getElementById('adoDate').textContent = adopt.ado_date;
            document.getElementById('adoStat').textContent = adopt.ado_stat === 1 ? "신청" :
                                                                            adopt.ado_stat === 2 ? "승인" :
                                                                            adopt.ado_stat === 3 ? "반려" : "기타";


            const appBtn = document.getElementById('appbtn');
            const rejectBtn = document.getElementById('rejectBtn');

            if (adopt.ado_stat !== 1) {
                // ado_stat이 1이 아니면 버튼 숨기기
                appBtn.style.display = 'none';
                rejectBtn.style.display = 'none';
            } else {
                // ado_stat이 1이면 버튼 보이기
                appBtn.style.display = 'inline-block';
                rejectBtn.style.display = 'inline-block';
            }
            console.log(adopt)
            // 모달을 띄운다.
            const myModal = new bootstrap.Modal(document.getElementById('adoptInfoModal'));
            myModal.show();

        })
        .catch(error => {
            console.error('Error:', error);
        });

}
document.getElementById('appbtn').addEventListener('click', function() {
    // 승인 버튼을 클릭했을 때 adoStat = 2
    const adono = document.getElementById('adoptNo').textContent
    let adoStat = this.getAttribute('data-adoStat');  // 버튼의 data-adoStat 속성 값 가져오기 (2)

    // 서버에 요청 보내기
    fetch(`/adopt/updateAdoStat/${adono}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ ado_stat: adoStat })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('승인 하셨습니다.');
                window.location.href = '/adopt/list'; // 성공 시 adopt/list로 리디렉션
            } else {
                alert('업데이트 실패');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

document.getElementById('rejectBtn').addEventListener('click', function() {
    // 반려 버튼을 클릭했을 때 adoStat = 3
    const adono = document.getElementById('adoptNo').textContent
    let adoStat = this.getAttribute('data-adoStat');  // 버튼의 data-adoStat 속성 값 가져오기 (3)

    // 서버에 요청 보내기
    fetch(`/adopt/updateAdoStat/${adono}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ ado_stat: adoStat })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('반려 하셨습니다.');
                window.location.href = '/adopt/list'; // 성공 시 adopt/list로 리디렉션
            } else {
                alert('업데이트 실패');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});