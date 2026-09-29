function PersonCard({ person }) {
    const group = person.groupName ?? person.group ?? '-';
    const skills = person.skills ?? [];

    return (
        <div className="person-card">

            <h2>
                {person.firstName} {person.lastName}
            </h2>

            <p>
                <strong>Email:</strong> {person.email}
            </p>

            <p>
                <strong>Group:</strong> {group}
            </p>

            <div className="skills">
                {skills.map((skill) => (
                    <span className="skill" key={skill}>
                        {skill}
                    </span>
                ))}
            </div>

        </div>
    );
}

export default PersonCard;