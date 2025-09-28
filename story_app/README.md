# Interactive Storytelling Application for Kids

This repository contains the complete source code for the Interactive Storytelling Application, a platform designed to provide an engaging reading experience for children across an Android app and a web-based PWA.

The project is divided into three main components:
1.  **Backend (PHP + MySQL)**: A REST API and Admin Panel.
2.  **Android App (Java)**: A native Android application for phones and tablets.
3.  **Frontend Website (HTML/CSS/JS)**: A public-facing website with PWA support.

---

## 1. Backend Setup

The backend powers the entire application, providing data to the clients and an administrative interface for content management.

### Prerequisites
- A web server with PHP support (e.g., Apache, Nginx).
- A MySQL database server.
- A tool to manage your database (e.g., phpMyAdmin).

### Steps
1.  **Deploy Files**: Copy the entire contents of the `story_app/backend` directory to the root of your web server (e.g., `/var/www/html/` or `htdocs/`).
2.  **Create Database**:
    -   Open your MySQL management tool.
    -   Create a new database named `interactive_story_db`.
3.  **Import Schema**:
    -   In the newly created database, import the `story_app/backend/database.sql` file. This will create all the necessary tables.
4.  **Configure Connection**:
    -   Open `story_app/backend/config/database.php`.
    -   Update the `DB_USER` and `DB_PASS` constants with your MySQL username and password.
5.  **Create an Admin User**:
    -   To log in to the admin panel, you must create a secure admin user.
    -   **Generate a Hashed Password**: Navigate to `http://your-domain.com/backend/admin/hash_password.php` in your browser. This script will generate a secure hash for a default password. Copy this hash. (For better security, you can edit the script to hash your own chosen password).
    -   **Insert Admin User**: Using your database management tool, run the following SQL command, replacing `'your_hashed_password_here'` with the hash you copied:
        ```sql
        INSERT INTO `admin_users` (`username`, `password`) VALUES ('admin', 'your_hashed_password_here');
        ```
6.  **Access Admin Panel**:
    -   Navigate to `http://your-domain.com/backend/admin/` in your browser.
    -   Log in with the credentials you just created. From the dashboard, you can manage stories, categories, languages, and the individual pages within each story.

---

## 2. Android App Setup

### Prerequisites
- Android Studio.
- An Android device or emulator with an active internet connection for the first run/download.

### Steps
1.  **Open the Project**:
    -   The `story_app/android_app` directory contains the source files for the Android project, but it is not a complete, self-contained Android Studio project.
    -   The recommended approach is to create a new, empty project in Android Studio and then copy the files from `story_app/android_app` into the `app` module of your new project.
2.  **Copy Source Files**:
    -   Create a new project in Android Studio.
    -   Navigate to the project's `app` folder in your file explorer.
    -   Replace the `build.gradle` file and the `src` directory with the ones from `story_app/android_app`.
3.  **Update API URL**:
    -   Open `src/main/java/com/storyapp/kids/network/ApiClient.java`.
    -   Change the `BASE_URL` constant to point to your backend's API directory.
        -   For an emulator connecting to a localhost server, the URL `http://10.0.2.2/story_app/backend/api/` is correct.
        -   For a physical device, you must use the local IP address of your server (e.g., `http://192.168.1.10/story_app/backend/api/`).
        -   For a live server, use your public domain name.
4.  **Offline Functionality**:
    -   The app supports full offline viewing of stories.
    -   From the story detail screen, click the "Download" button. This will save the story's text, images, and audio files to the device.
    -   If the app is launched without an internet connection, it will display a list of all downloaded stories.
5.  **Build and Run**:
    -   Sync the project with Gradle files in Android Studio.
    -   Build and run the application on your device or emulator.

---

## 3. Frontend Website Setup

### Prerequisites
- A web server (can be the same one used for the backend).
- A modern web browser.

### Steps
1.  **Deploy Files**:
    -   Copy the contents of the `story_app/frontend_website` directory to a folder on your web server.
2.  **Update API URL**:
    -   Open `frontend_website/js/app.js`.
    -   Update the `API_BASE_URL` constant to point to your backend API. Make sure this is a public-facing URL.
        ```javascript
        const API_BASE_URL = 'http://your-domain.com/backend/api';
        ```
3.  **Access the Website**:
    -   Navigate to the corresponding URL in your browser (e.g., `http://your-domain.com/frontend/`).

---

## 4. Scope and Future Enhancements

This project delivers the core functionality for an interactive storytelling application. Key implemented features include:

-   **Admin Panel**: Secure login and full CRUD management for stories, categories, languages, and individual story pages.
-   **REST API**: Provides all necessary data to the client applications.
-   **Android App**: Language selection, story browsing, a story reader with page-swiping, and full offline support for text, images, and audio.
-   **Frontend PWA**: Story browsing and reading, with offline caching of the app shell and viewed content.

The following features from the original comprehensive request are considered **out of scope** for this version but could be implemented as future enhancements:

-   **Kid / Parent Profiles**: A multi-user system for children and parents.
-   **Gamification**: A rewards system with stars and badges.
-   **Advanced Accessibility**: Controls for adjusting font size and speech speed.
-   **Story Recommendations**: A system to suggest related or popular stories.
-   **Admin Analytics**: A dashboard for tracking popular stories, categories, and languages.

---

## 5. API Endpoints

The backend provides the following REST API endpoints:

-   `GET /api/languages.php`: Fetches all available languages.
-   `GET /api/categories.php`: Fetches all available categories.
-   `GET /api/stories.php`: Fetches stories.
    -   **Query Parameters**:
        -   `language_id` (int): Filter by language.
        -   `category_id` (int): Filter by category.
-   `GET /api/story_details.php`: Fetches the full details of a single story, including pages and audio.
    -   **Query Parameters**:
        -   `id` (int): The ID of the story to fetch.