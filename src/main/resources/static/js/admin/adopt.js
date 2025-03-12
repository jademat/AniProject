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

            console.log(adopt)
            // 모달을 띄운다.
            const myModal = new bootstrap.Modal(document.getElementById('adoptInfoModal'));
            myModal.show();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
