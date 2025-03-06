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

