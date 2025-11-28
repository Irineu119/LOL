var image = null;
var jaJogou = false;

function main() {
    image = document.getElementById("image");

    document.getElementById("aiButton").addEventListener("click", function() {
        if (jaJogou)
            return;

        if (image.src.includes("ai_images/ai_champ.png"))
            alert("Acertou!");
        else
            alert("Errou...");

        jaJogou = true;
    });

    document.getElementById("realButton").addEventListener("click", function() {
        if (jaJogou)
            return;

        if (!image.src.includes("ai_images/ai_champ.png"))
            alert("Acertou!");
        else
            alert("Errou...");
        
        jaJogou = true;
    });
}

window.onload = main;