document.addEventListener("DOMContentLoaded", function(event) {


    // const reservationDay = document.getElementById("reservationDay");
    // const reservationType = document.getElementById("reservationType");
    // reservationDay.addEventListener("click", (e) => {
    //     const value = reservationDay.value;
    //     console.log(value);
    //     if (value !== "SATURDAY" || value !== "SUNDAY") {
    //         reservationType.style.display = "block";
    //     }else {
    //         reservationType.style.display = "none";
    //     }
    // })
    /** hide reseration type when the day choosed is not saturday or sunday */
    const selectreservationDay = document.getElementById("reservationDay");
    const selectReservationType = document.getElementById("selectReservationType");
    selectreservationDay.addEventListener("change", (e) =>{
        let value = selectreservationDay.value;
        console.log(value);
        if (value !== "SATURDAY" && value !== "SUNDAY") {
            selectReservationType.classList.remove("hide");
        }else {
            selectReservationType.classList.add("hide");
        }
    })



    const modal = document.getElementById("showUpdateModal");
    modal.addEventListener("click", () =>{
        $('#uppdateReservationType').modal('show');
    })


    /** open connection to the server */
    let url = 'ws://' + window.location.host + '/sendNotification';
    let sock = new WebSocket(url);
    sock.onopen = function() {
        console.log('Opening');
        sock.send("hello server");
    }

    sock.onmessage = (e) =>{
        console.log(e.data);
    }

    sock.onclose = (e) =>{
        console.log("connection is closed");
    }
    /***/

});



