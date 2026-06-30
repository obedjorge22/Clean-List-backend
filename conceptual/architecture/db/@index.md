## Case Study

The proposed **CleanList** system aims to centralize and simplify the management of cleaning schedules for organizations, schools, or any group that requires recurring cleaning duties. The platform organizes members into groups, generates fair cleaning rotations, and maintains a complete history of assignments, ensuring transparency, accountability, and ease of administration.

The system is centered around a **Cleaning List**, which represents an organization or department responsible for managing cleaning activities. Each cleaning list contains its own members, groups, schedules, and cleaning history, allowing multiple independent lists to coexist within the same application.

Each member is registered with personal information such as name, contact details, and active status. Members may belong to one or more groups depending on the organization's rules. Groups are used to organize participants and distribute cleaning responsibilities fairly among all members.

Cleaning schedules define when cleaning activities should occur. A schedule includes the cleaning date, status, and any observations related to the assignment. The system automatically associates one or more groups with each scheduled cleaning event according to the configured rotation rules.

For every scheduled cleaning, the system generates assignments that indicate which group is responsible. Each assignment records its creation date, execution status, completion date, and optional notes, allowing administrators to track whether cleaning duties were completed successfully.

The application maintains a complete history of all cleaning assignments, enabling administrators to review past schedules, identify which groups participated on specific dates, and generate reports for accountability and planning purposes.

The system supports different user roles. Administrators can manage cleaning lists, members, groups, schedules, and assignments, while regular members can view their assigned cleaning dates and the history of their group's participation. Authentication is performed securely using JWT, ensuring that only authorized users can access protected resources.

Business rules ensure that each cleaning schedule belongs to one cleaning list, each assignment references a single schedule and a single group, and every group may participate in many assignments over time. Likewise, each member may belong to one or more groups, depending on the organization's configuration.

By centralizing cleaning schedules and automating assignment rotation, CleanList reduces manual work, prevents scheduling conflicts, improves fairness in task distribution, and provides a reliable historical record of all cleaning activities.