function SearchBar({ search, setSearch }) {
    return (
        <div className="search-container">
            <input
                type="text"
                placeholder="Search by name, group or skill..."
                value={search}
                onChange={(event) => setSearch(event.target.value)}
            />
        </div>
    );
}

export default SearchBar;