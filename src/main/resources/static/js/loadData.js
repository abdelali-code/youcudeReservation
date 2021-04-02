document.addEventListener("DOMContentLoaded", function(event) {
    const elem = document.getElementById("profileLink");
    const notificationDropdown = document.getElementById("notificationDropdown");
    elem.addEventListener("click", function () {
        fetch("/notification")
            .then((result) => {
                if (result.status) {
                    return result.json();
                }else {
                    throw new Error("somethig bad happen try again or try later");
                }
            })
            .then((data) => {
                const node = document.createElement("ul");
                node.classList.add("dropdown-menu");
                data.forEach(dt => {
                    const node = document.createElement("li");
                    node.classList.add("dropdown-item");
                    const tem = document.createTextNode(dt.content);
                    node.appendChild(tem);
                    notificationDropdown.appendChild(node);
                })
            })
    })
});



