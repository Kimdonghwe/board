  const $addBtn = document.getElementById('addBtn');
  $addBtn.addEventListener('click', evt=>{
    location.href = '/products/add';                // GET   http://localhost:9080/products/add
  });

  const $rows = document.getElementById('rows');
  $rows.addEventListener('click',evt=>{
    //1) input요소 이면서 checkbox는 제외
    if(evt.target.tagName === 'INPUT' &&  evt.target.getAttribute('type') == 'checkbox') {
      return;
    };
    //2) td요소중  checkbox있는 td는 제외
    if(evt.target.tagName == 'TD' && evt.target.classList.contains('chk')){
      return;
    }
    const $row = evt.target.closest('.row');
    const productId = $row.dataset.productId;
    location.href = `/products/${productId}/detail`;    // GET http://localhost:9080/상품번호/detail
  });

  //삭제
  const frm = document.getElementById('frm');
  frm.addEventListener('submit', evt=>{
    evt.preventDefault();  //기본 이벤트(submit) 중지
    if(!window.confirm('삭제하시겠습니까?')) return;
    evt.target.submit();
  });

  //전체선택
  //일부 체크박스가 체크되어있다면 언체크, 그렇지 않으면 모든 체크박스를 체크
  const $selectAll = document.getElementById('selectAll');
  $selectAll.addEventListener('click',evt=>{
    //Array.from(iteral) : iteral객체를 배열로 변환
    const $inputs = Array.from(document.querySelectorAll('#rows input[type=checkbox]'));
    const isSomeChecked = $inputs.some(input=>input.checked == true)
    if(isSomeChecked) {
      $inputs.forEach(input=>input.checked = false);  // 일부 체크박스가 uncheck면 모든 체크박스를 unchecked 변경
    }else{
      $inputs.forEach(input=>input.checked = true);  // 모든 체크박스를 checked로 변경
    }
  });