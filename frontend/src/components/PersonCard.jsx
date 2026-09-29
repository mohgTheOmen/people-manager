function PersonCard({ person }) {
    return (
        <div className="person-card">

            <h2>
                {person.firstName} {person.lastName}
            </h2>

            <p>
                <strong>Email:</strong> {person.email}
            </p>

            <p>
                <strong>Group:</strong> {person.group}
            </p>

            <div className="skills">
                {person.skills.map((skill) => (
                    <span className="skill" key={skill}>
                        {skill}
                    </span>
                ))}
            </div>

        </div>
    );
}

export default PersonCard;