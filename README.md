# 🧹 CleanList

**CleanList** is a platform designed to manage cleaning schedules for organizations.

Its goal is to replace traditional paper schedules with a modern, easy-to-use system where each congregation can manage its own members, groups, and cleaning schedules independently.

---

# ✨ Features

## Cleaning List Management

Each cleaning list contains:

- Name
- Access password
- Delegate (administrator)
- Members
- Groups
- Cleaning schedules

Every cleaning list is completely isolated from the others.

---

# 👥 User Roles

## System Administrator

Responsible for managing the entire platform.

Capabilities:

- Manage all cleaning lists
- Remove cleaning lists
- Block or deactivate lists
- Recover access
- Monitor system usage

---

## Delegate

The delegate is the administrator of a specific cleaning list.

Capabilities:

- Create, edit, and remove groups
- Register and manage members
- Activate or deactivate members
- Assign members to groups
- Generate cleaning schedules
- Manage list settings
- View schedule history

---

## Member

Members have access only to their own information.

Capabilities:

- Sign in to their cleaning list
- View upcoming cleaning schedules
- View their assigned group
- Check the cleaning calendar
- Receive notifications (planned)

---

# 🏗️ System Architecture

```
System

└── Cleaning List
      ├── Delegate
      ├── Members
      ├── Groups
      └── Schedules
```

Each cleaning list has its own data and operates independently.

---

# 📂 Domain Model

```
CleaningList
│
├── Delegate
├── Member
├── Group
└── Schedule
```

---

# 🚀 Technology Stack

## Backend

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

## Web Frontend

- React
- Tailwind CSS
- Axios
- React Router

---

## Mobile

- Flutter
- Dart

---

# 📱 Supported Platforms

- 🌐 Web
- 📱 Android
- 🍎 iOS (planned)

---

# 🔐 Authentication & Authorization

The application provides role-based access control.

### Administrator

- Full system access
- Manage every cleaning list
- System maintenance

### Delegate

- Full control over a single cleaning list
- Manage members
- Manage groups
- Generate schedules

### Member

- Read-only access to personal schedule information

---

# 📅 Smart Scheduling

CleanList is designed to automate schedule generation.

Planned features include:

- Automatic schedule generation
- Group rotation
- Holiday skipping
- Automatic rescheduling
- Schedule history
- Yearly schedule generation

---

# 🛣️ Roadmap

## Version 1.0

- [ ] Authentication
- [ ] Cleaning list creation
- [ ] Member management
- [ ] Group management
- [ ] Assign members to groups
- [ ] Automatic schedule generation
- [ ] Calendar view

---

## Version 1.1

- [ ] Notifications
- [ ] Dashboard
- [ ] Reports
- [ ] Statistics

---

## Version 2.0

- [ ] Flutter mobile application
- [ ] Push notifications
- [ ] Schedule sharing
- [ ] Multiple delegates per list
- [ ] Cloud backup

---

# 💡 Vision

Our mission is to simplify cleaning schedule management by providing a digital platform that is organized, intuitive, and accessible from anywhere.

---

# 🎯 Future Features

- Multi-language support
- Dark mode
- QR Code access
- Attendance confirmation
- Schedule swapping
- Export schedules as PDF
- Email notifications
- WhatsApp integration
- Analytics dashboard

---

# 🤝 Contributing

Contributions are welcome!

If you'd like to improve CleanList:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Push your branch.
5. Open a Pull Request.

---

# 📄 License

This project is licensed under the MIT License.

---

## ❤️ Built With

- Java
- Spring Boot
- React
- Flutter
- MySQL

Made with ❤️ to help communities organize their cleaning schedules efficiently.