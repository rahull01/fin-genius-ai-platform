function toggleTheme() {
    let theme = document.documentElement.getAttribute("data-theme");

    if (theme === "dark") {
        document.documentElement.setAttribute("data-theme", "light");
        localStorage.setItem("theme", "light");
    } else {
        document.documentElement.setAttribute("data-theme", "dark");
        localStorage.setItem("theme", "dark");
    }
}

// apply saved theme
window.onload = () => {
    let saved = localStorage.getItem("theme") || "light";
    document.documentElement.setAttribute("data-theme", saved);
};
