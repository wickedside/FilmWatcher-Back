const API_KEY = "8c8e1a50-6322-4135-8875-5d40a5420d86";
const API_URL_POPULAR =
    "https://kinopoiskapiunofficial.tech/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS&page=1";
const API_URL_SEARCH =
    "https://kinopoiskapiunofficial.tech/api/v2.1/films/search-by-keyword?keyword=";
const API_URL_MOVIE_DETAILS = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"

document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const movieId = urlParams.get("id");

    if (movieId) {
        loadMovieDetails(movieId);
    }
});

async function loadMovieTrailers(id) {
    const resp = await fetch(`https://kinopoiskapiunofficial.tech/api/v2.2/films/${id}/videos`, {
        headers: {
            "Content-Type": "application/json",
            "X-API-KEY": API_KEY,
        },
    });
    const trailersData = await resp.json();
    console.log(trailersData)
    return trailersData;
}

async function showMovieTrailers(id) {
    const trailersData = await loadMovieTrailers(id);
    const trailerContainer = document.querySelector(".movie-trailer");

    // Очистка контейнера перед добавлением трейлера
    trailerContainer.innerHTML = '';

    // Поиск дублированного трейлера на YouTube
    let trailerToShow = trailersData.items.find(trailer => trailer.site === "YOUTUBE" && trailer.name.includes("дублированный"));

    // Если дублированный трейлер не найден, выбираем первый попавшийся трейлер на YouTube
    if (!trailerToShow) {
        trailerToShow = trailersData.items.find(trailer => trailer.site === "YOUTUBE");
    }

    // Если трейлер на YouTube не найден, проверяем Yandex Disk
    if (!trailerToShow) {
        trailerToShow = trailersData.items.find(trailer => trailer.site === "YANDEX_DISK");
    }

    if (trailerToShow) {
        let embedUrl;
        if (trailerToShow.site === "YOUTUBE") {
            embedUrl = convertYoutubeUrlToEmbedUrl(trailerToShow.url);
        } else if (trailerToShow.site === "YANDEX_DISK") {
            embedUrl = trailerToShow.url; // Yandex Disk видео могут быть встроены напрямую
        }

        const trailerIframe = document.createElement("iframe");
        trailerIframe.setAttribute("src", embedUrl);
        trailerIframe.setAttribute("width", "560");
        trailerIframe.setAttribute("height", "315");
        trailerIframe.setAttribute("frameborder", "0");
        trailerIframe.setAttribute("allowfullscreen", "");
        trailerContainer.appendChild(trailerIframe);
    } else {
        // Если трейлер не найден
        trailerContainer.textContent = 'Трейлер не найден.';
    }
}

// Функция для конвертации обычной ссылки YouTube в ссылку для встраивания видео
function convertYoutubeUrlToEmbedUrl(youtubeUrl) {
    const urlObj = new URL(youtubeUrl);
    const videoId = urlObj.searchParams.get("v") || urlObj.pathname.split('/')[1];
    return `https://www.youtube.com/embed/${videoId}`;
}

async function loadMovieDetails(id) {
    const resp = await fetch("https://kinopoiskapiunofficial.tech/api/v2.2/films/" + id, {
        headers: {
            "Content-Type": "application/json",
            "X-API-KEY": API_KEY,
        },
    });
    const movie = await resp.json();

    // Заполнение элементов на странице информацией о фильме
    document.querySelector(".movie-title").textContent = movie.nameRu;
    const movieTitleEn = movie.nameOriginal ? `(${movie.nameOriginal})` : '';
    document.querySelector(".movie-title-en").textContent = movieTitleEn;
    const movieSlogan = movie.slogan ? `Слоган: ${movie.slogan}` : '';
    document.querySelector(".movie-slogan").textContent = movieSlogan;
    document.querySelector(".movie-year").textContent = `Год: ${movie.year}`;
    document.querySelector(".movie-country").textContent = `Страна: ${movie.countries.map(country => country.country).join(", ")}`;
    // Загрузка информации о бюджете фильма
    const budgetResponse = await fetch(`https://kinopoiskapiunofficial.tech/api/v2.2/films/${id}/box_office`, {
        headers: {
            "Content-Type": "application/json",
            "X-API-KEY": API_KEY,
        },
    });
    const budgetData = await budgetResponse.json();

    const budgetItem = budgetData.items.find(item => item.type === "BUDGET");
    const movieBudgetElement = document.querySelector(".movie-budget");
    if (budgetItem && budgetItem.amount > 0) {
        movieBudgetElement.textContent = `Бюджет: ${budgetItem.amount.toLocaleString()} ${budgetItem.currencyCode} $`;
    } else {
        movieBudgetElement.style.display = 'none'; // Скрываем элемент, если бюджет неизвестен
    }
    document.querySelector(".movie-image img").src = movie.posterUrl;
    document.querySelector(".movie-description").textContent = `Описание: ${movie.description}`;
    document.querySelector(".movie-rating-kp").textContent = `Рейтинг Кинопоиск: ${movie.ratingKinopoisk}`;
    document.querySelector(".movie-rating-imdb").textContent = `Рейтинг IMDb: ${movie.ratingImdb}`;
    await showMovieTrailers(id);
}
