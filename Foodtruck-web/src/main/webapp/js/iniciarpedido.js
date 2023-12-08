productos = document.querySelectorAll('.producto__container')

console.log(productos)

productos.forEach(producto => {
    console.log(producto)

    producto.addEventListener("click",(e)=>{
        
        console.log(e.target)

        e.target.parentNode.classList
        inputs = document.querySelectorAll("input")


        inputs.forEach(input => {
            if(input.id == e.target.id && input.checked == false){
                input.checked = true

                e.target.parentNode.classList.add("check")

            } else if(input.id == e.target.id){
                input.checked = false
                e.target.parentNode.classList.remove("check")
            }
        });


    })

});