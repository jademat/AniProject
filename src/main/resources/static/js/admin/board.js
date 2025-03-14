function BoardDetailModal(bd_no) {
    fetch(`/adboard/view/${bd_no}`)
        .then(response => response.json())
        .then(board => {
            // 모달 내용 갱신
            document.getElementById('bdNo').textContent = board.bd_no;
            document.getElementById('userId').textContent = board.userid;
            document.getElementById('bdTitle').textContent = board.bd_title;
            document.getElementById('bdCont').textContent = board.bd_contents;
            document.getElementById('bdRegdate').textContent = board.bd_regdate;
            document.getElementById('bdThumbs').textContent = board.bd_thumbs;
            document.getElementById('bdViews').textContent = board.bd_views;
            document.getElementById('bdReport').textContent = board.bd_report;



            // 모달 창 띄우기
            const modal = new bootstrap.Modal(document.getElementById('BoardDetailModal'));

            modal.show();
        })
}

function confirmDelete() {
    if (confirm("정말 삭제하시겠습니까?")) {
        // DELETE 요청 보내기
        let form = document.getElementById('deleteForm');
        fetch(form.action, {
            method: 'Post', // 실제 DELETE 요청을 보냄
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    alert("게시물이 삭제되었습니다.");
                    window.location.href = '/adboard/boardlist';  // 삭제 후 목록 페이지로 리디렉션
                } else {
                    alert('삭제에 실패했습니다.');
                }
            })
            .catch(error => alert('삭제 중 오류가 발생했습니다.'));
    }
}