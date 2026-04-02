let selectedRow = null;

// ================== THÊM ==================
document.getElementById("formKH").addEventListener("submit", function(e) {
    e.preventDefault();


    let data = new URLSearchParams();
    data.append("action", "them");
    data.append("ma", document.querySelector('input[name="ma"]').value);
    data.append("hoten", document.querySelector('input[name="hoten"]').value);
    data.append("sdt", document.querySelector('input[name="sdt"]').value);
    data.append("diachi", document.querySelector('input[name="diachi"]').value);

    fetch(contextPath +"/khachhang", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: data
    })
    .then(res => res.json())
    .then(kh => {
            // thêm dòng mới
            if (kh.error === "trung_ma") {
                document.getElementById("errorMa").innerText = "Mã khách hàng đã tồn tại!";
                return;
            } else {
                document.getElementById("errorMa").innerText = "";
            }
            let tbody = document.querySelector("#bangKH tbody");
            let row = tbody.insertRow();

            row.insertCell(0).innerText = kh.ma;
            row.insertCell(1).innerText = kh.hoten;
            row.insertCell(2).innerText = kh.sdt;
            row.insertCell(3).innerText = kh.diachi;

            let cellAction = row.insertCell(4);
            cellAction.innerHTML = `
                <button onclick="xoa('${kh.ma}', this)">Xóa</button>
                <button onclick="suaRow(this)">Sửa</button>
            `;
    });
    document.getElementById("formKH").reset();
});


// ================== XÓA ==================
function xoa(ma, btn) {
    if (!confirm("Xóa khách hàng?")) return;

    fetch(contextPath +"/khachhang?action=xoa&ma=" + ma)
    .then(res => res.json())
    .then(() => {
        btn.closest("tr").remove();
    });
}


// ================== SỬA ==================
function suaRow(btn) {
    document.getElementById("btnSua").disabled=false;
    selectedRow = btn.closest("tr");
    
    document.querySelector('input[name="ma"]').value = selectedRow.cells[0].innerText;
    document.querySelector('input[name="hoten"]').value = selectedRow.cells[1].innerText;
    document.querySelector('input[name="sdt"]').value = selectedRow.cells[2].innerText;
    document.querySelector('input[name="diachi"]').value = selectedRow.cells[3].innerText;
    //khoa ma
    document.querySelector('input[name="ma"]').readOnly = true;
}

document.getElementById("btnSua").addEventListener("click", function () {
    
    if (!selectedRow) {
        alert("Chọn khách hàng để sửa!");
        return;
    }

    let data = new URLSearchParams();
    data.append("action", "sua");
    data.append("ma", document.querySelector('input[name="ma"]').value);
    data.append("hoten", document.querySelector('input[name="hoten"]').value);
    data.append("sdt", document.querySelector('input[name="sdt"]').value);
    data.append("diachi", document.querySelector('input[name="diachi"]').value);

    fetch(contextPath + "/khachhang", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: data
    })
    .then(res => res.json())
    .then(kh => {
        selectedRow.cells[1].innerText = kh.hoten;
        selectedRow.cells[2].innerText = kh.sdt;
        selectedRow.cells[3].innerText = kh.diachi;

        // reset
        document.getElementById("formKH").reset();
        document.querySelector('input[name="ma"]').readOnly = false;
        selectedRow = null;
    });
    document.getElementById("btnSua").disabled=true;
});
document.getElementById("search").addEventListener("keyup", function () {
    let key = this.value.toLowerCase();

    let rows = document.querySelectorAll("#bangKH tbody tr");

    rows.forEach(row => {
        let ma = row.cells[0].innerText.toLowerCase();
        let hoten = row.cells[1].innerText.toLowerCase();

        if (ma.includes(key) || hoten.includes(key)) {
            row.style.display = ""; // hiện
        } else {
            row.style.display = "none"; // ẩn
        }
    });
});
function hienBang(){
    let rows = document.querySelectorAll("#bangKH tbody tr");
    rows.forEach(row => {
        row.style.display = ""; // hiện
    });
}
document.getElementById("reset").addEventListener("click",function(){
   document.getElementById("btnSua").disabled=true;
   document.getElementById("btnThem").disabled=false;
   document.querySelector('input[name="ma"]').readOnly = false;
   document.getElementById("search").value="";
   document.getElementById("formKH").reset();
   hienBang();
});