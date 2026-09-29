import './App.css'
import PersonCard from './components/PersonCard'
import SearchBar from './components/SearchBar'
import { useEffect, useState } from 'react'

function App() {
    const [search, setSearch] = useState('')
    const [people, setPeople] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        const baseUrl = import.meta.env.VITE_API_BASE_URL || '/api'

        fetch(`${baseUrl}/people`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Request failed with status ${response.status}`)
                }
                return response.json()
            })
            .then((data) => {
                setPeople(data)
                setError('')
            })
            .catch(() => {
                setError('Failed to load people from backend.')
            })
            .finally(() => {
                setLoading(false)
            })
    }, [])

    const filteredPeople = people.filter((person) => {
        const query = search.toLowerCase();
        const fullName =
            `${person.firstName} ${person.lastName}`.toLowerCase();
        const matchesName =
            fullName.includes(query);
        const group =
            (person.groupName ?? person.group ?? '').toLowerCase();
        const matchesGroup =
            group.includes(query);
        const matchesSkills =
            (person.skills ?? []).some((skill) =>
                skill.toLowerCase().includes(query)
            );
        return matchesName || matchesGroup || matchesSkills;
    });

    return (
        <div className="container">
            <header>
                <h1>Group Finder</h1>
                <p>Find people by name, group or skills.</p>
            </header>

            <SearchBar
                search={search}
                setSearch={setSearch}
            />

            {loading && <p>Loading people...</p>}
            {!loading && error && <p className="status-message error">{error}</p>}
            {!loading && !error && filteredPeople.length === 0 && (
                <p className="status-message">No people found.</p>
            )}

            <main className="people-grid">
                {filteredPeople.map((person) => (
                    <PersonCard
                        key={person.id}
                        person={person}
                    />
                ))}
            </main>
        </div>
    )
}

export default App