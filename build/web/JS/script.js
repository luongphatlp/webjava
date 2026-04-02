// Mở modal thêm
function openModal() {
    document.getElementById("addModal").style.display = "block";
}

function closeModal() {
    document.getElementById("addModal").style.display = "none";
}

// Mở modal sửa và đổ dữ liệu từ các cột vào input
function openEditModal(ma, ten, dc, sdt) {
    document.getElementById("edit_ma").value = ma;
    document.getElementById("edit_ten").value = ten;
    document.getElementById("edit_dc").value = dc;
    document.getElementById("edit_sdt").value = sdt;
    document.getElementById("editModal").style.display = "block";
}

function closeEditModal() {
    document.getElementById("editModal").style.display = "none";
}

// Đóng modal khi click ra vùng ngoài
window.onclick = function(event) {
    let addM = document.getElementById("addModal");
    let editM = document.getElementById("editModal");
    if (event.target == addM) addM.style.display = "none";
    if (event.target == editM) editM.style.display = "none";
}

// Thay thế hàm closeModal trong script.js
function closeModal() {
    let modal = document.getElementById("addModal");
    modal.style.opacity = "0"; // Tạo hiệu ứng mờ dần
    setTimeout(() => {
        modal.style.display = "none";
        modal.style.opacity = "1";
    }, 200);
}

function closeEditModal() {
    let modal = document.getElementById("editModal");
    modal.style.opacity = "0";
    setTimeout(() => {
        modal.style.display = "none";
        modal.style.opacity = "1";
    }, 200);
}

function showToast(message, type = 'success') {
    const toast = document.getElementById("toast");
    toast.innerText = message;
    
    // Thêm class dựa trên loại thông báo
    toast.className = "show " + (type === 'success' ? "toast-success" : "toast-error");
    
    // Sau 2.4 giây thì xóa class để ẩn hẳn
    setTimeout(function() {
        toast.className = toast.className.replace("show", "");
    }, 2400); 
}