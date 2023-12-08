x = document.querySelector("#local")
console.log(x)

y = document.querySelector("#delivery")
console.log(y)



x.addEventListener("click", () =>{
    console.log("CLICK")
    local = document.querySelector("#pres")
    del = document.querySelector("#del")
    local.checked = true

    x.classList.add("elegido")
    if (y.classList.contains("elegido")){
        y.classList.remove("elegido")
    }

    console.log("Radio local: " + local.checked)
    console.log("Radio del: " + del.checked)

})

y.addEventListener("click", () =>{
    console.log("CLICK")
    local = document.querySelector("#pres")
    del = document.querySelector("#del")
    del.checked = true

    y.classList.add("elegido")
    if (x.classList.contains("elegido")){
        x.classList.remove("elegido")
    }

    console.log("Radio local: " + local.checked)
    console.log("Radio del: " + del.checked)
})



mases = document.querySelectorAll(".fa-plus")


menoses = document.querySelectorAll(".fa-minus")

mases.forEach(mas => {
    console.log(mas)
    mas.addEventListener("click", ()=>{
        cantidad = mas.previousElementSibling
        cantidad.value++

        elementoTotal = document.querySelector(".numero")
        total = 0
        precios = document.querySelectorAll(".precio")
        precios.forEach(precio =>{
            //console.log(precio.parentNode.parentNode.parentNode.nextElementSibling.childNodes[3].value)
            cantidad = precio.parentNode.parentNode.parentNode.nextElementSibling.childNodes[3].value
            //console.log(precio.textContent)
            total += cantidad * precio.textContent
        })
        
        elementoTotal.textContent = total
        
        
});

})

menoses.forEach(menos => {
    menos.addEventListener("click", ()=>{

        cantidad = menos.nextElementSibling
        if(cantidad.value>1){
            cantidad.value--
        }

        elementoTotal = document.querySelector(".numero")
        total = 0
        precios = document.querySelectorAll(".precio")
        precios.forEach(precio =>{
            //console.log(precio.parentNode.parentNode.parentNode.nextElementSibling.childNodes[3].value)
            cantidad = precio.parentNode.parentNode.parentNode.nextElementSibling.childNodes[3].value
            //console.log(precio.textContent)
            total += cantidad * precio.textContent
        })
        
        elementoTotal.textContent = total
        
        
    })
});

