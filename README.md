# VendiApp

## 📌 Descriere
VendiApp este o aplicație de tip marketplace (gen OLX), împărțită în două componente principale:
- **FrontEnd** – aplicație Android scrisă în Java  
- **Backend** – API Python pentru gestionarea anunțurilor și comunicarea cu aplicația mobilă  

Proiectul este în dezvoltare și reprezintă baza unui sistem complet de postare și administrare anunțuri.

---

## 🧱 Arhitectură

### ✔ FrontEnd (Android)
- Java  
- Android SDK  
- Comunicarea cu backend-ul prin HTTP/JSON  
- Liste produse, detalii, ecrane UI  

### ✔ Backend (Python)
- API REST (FastAPI / Flask)  
- Manipularea datelor: produse, utilizatori  
- Conectare la MySQL (sau alt RDBMS)  

---

## 📂 Structura proiectului

```
VendiApp/
├── FrontEnd/        # proiect Android (Java)
│   ├── app/
│   ├── gradle/
│   └── build.gradle
└── Backend/         # API Python
    ├── main.py
    ├── app/
    ├── requirements.txt
    └── ...
```

---