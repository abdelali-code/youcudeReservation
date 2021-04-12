document.addEventListener("DOMContentLoaded", function(event) {
    //<li><a class="dropdown-item" href="#">Action</a></li>
    const elem = document.getElementById("profileLink");
    const notificationDropdown = document.getElementById("dropDownNotification");

    notificationDropdown.addEventListener("click", function () {
        fetch("/notification")
            .then((result) => {
                if (result.status) {
                    return result.json();
                }else {
                    throw new Error("somethig bad happen try again");
                }
            })
            .then((data) => {
                const notificationList = document.getElementById("notificationList");
                notificationList.innerHTML = "";
                data.forEach(dt => {
                    const li = document.createElement("li");
                    li.classList.add("notif")
                    const a = document.createElement("li");
                    a.classList.add("dropdown-item");
                    const tem = document.createTextNode(dt.content);
                    a.appendChild(tem);
                    li.appendChild(a);
                    notificationList.appendChild(li);
                })
            })
    })

});



