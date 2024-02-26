document.addEventListener('DOMContentLoaded', renderHTML);
let $productList = '';
let $loadingImg = '';
renderHTML();
function renderHTML() {
    const $div = document.createElement('div');
    $div.innerHTML = `
  <h3> 상품등록 </h3>
  <form id="frm" action="">
  <div>
    <label for="">상품명</label>
    <input id="pname" name="pname" type="text">
  </div>
  <div>
    <label for="">수량</label>
    <input id="quantity" name="quantity" type="text">
  </div>
  <div>
    <label for="">가격</label>
    <input id="price" name="price" type="text">
  </div>
  <div><button id="addBtn" type="button">등록</button></div>

</form>
<div id="productList"></div>
<img id="loading" src="/img/loading.svg" >
`;

    document.body.appendChild($div);
    const $addBtn = $div.querySelector('#addBtn');
    $addBtn.addEventListener('click', (evt) => {
        console.log(evt);
        const formData = new FormData($div.querySelector('#frm'));
        const product = {
            pname: formData.get('pname'),
            quantity: formData.get('quantity'),
            price: formData.get('price'),
        };
        add(product);
    });

    //상품목록
    $productList = $div.querySelector('#productList');

    //로딩화면
    $loadingImg = $div.querySelector('#loading');
    $loadingImg.style.position = 'absolute';
    $loadingImg.style.top = '50vh';
    $loadingImg.style.left = '50vw';
    $loadingImg.style.transform = 'translate(-50%,-50%)';
    $loadingImg.style.display = 'none';

    list();
}
//목록
async function list() {
    $loadingImg.style.display = 'block';
    const url = 'http://localhost:9080/api/products';
    try {
        const res = await fetch(url, {
            method: 'GET',
            headers: {
                accept: 'application/json',
            },
        });

        const result = await res.json();
        if (result.header.rtcd == '00') {
            console.log(result.body);
            let $rowHTML = '';
            result.body.forEach((item) => {
                $rowHTML += `<div>
                        <span>${item.productId}</span>
                        <span>${item.pname}</span>
                        <span>${item.quantity}</span>
                        <span>${item.price}</span>
                        </div>`;
            });
            $productList.innerHTML = $rowHTML;
        } else {
            new Error('목록 실패!!');
        }
    } catch (err) {
        console.log(err.message);
    } finally {
        $loadingImg.style.display = 'none';
    }
}

//등록
async function add(product) {
    const url = 'http://localhost:9080/api/products';

    const payload = product;

    const option = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            accept: 'application/json',
        },
        body: JSON.stringify(payload),
    };

    try {
        const res = await fetch(url, option);
        if (!res.ok) return new Error('서버응답오류');
        const result = await res.json(); //응답메세지 바디를 읽어 json포맷 문자열=>js객체
        if (result.header.rtcd == '00') {
            console.log(result.body);
            list();
        } else {
            new Error('등록 실패!');
        }
    } catch (err) {
        console.log(err.message);
    }
}
// add();

//  조회

async function findbyId(pid) {
    const url = `http://localhost:9080/api/products/${pid}`;

    const option = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            accept: 'application/json',
        },
    };

    try {
        const res = await fetch(url, option);
        const result = await res.json();

        if (result.header.rtcd == '00') console.log(result.body);
        else if (result.header.rtcd == '01')
            console.log(result.header.rtmsg, result.header.rtdetail);
        else {
            new Error('조회 실패!!');
        }
    } catch (err) {
        console.log(err.message);
    }
}
// findbyId();

// 수정
async function update(pid) {
    const url = `http://localhost:9080/api/products/${pid}`;

    const payload = {
        pname: '플레이스테션PRO',
        quantity: 300,
        price: 600000,
    };

    const option = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            accept: 'application/json',
        },
        body: JSON.stringify(payload),
    };

    try {
        const res = await fetch(url, option);
        console.log(res);
        const result = await res.json();
        console.log(result);
    } catch (err) {
        console.log(err.message);
    }
}
// update();
// 삭제

async function remove(pid) {
    const url = `http://localhost:9080/api/products/${pid}`;

    const option = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            accept: 'application/json',
        },
    };

    try {
        const res = await fetch(url, option);
        const result = await res.json();
        console.log(result);
    } catch (err) {
        console.log(err.message);
    }
}
// remove();
