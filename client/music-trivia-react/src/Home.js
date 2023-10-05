function Home(){
    return (
        <>
            <div className="container-fluid">
                <h2>GAMER: {"this is where gamer goes?"}</h2>
                <h2>{"example tagline"}</h2>
            </div>
            <div className="container-fluid">
                <h1>High Scores</h1>
                <table>
                    <thead>
                        <th></th>
                        <th>column 1</th>
                        <th>column 2</th>
                        <th>column 3</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>sup</td>
                            <td>my</td>
                            <td>gamer</td>
                            <td>guy</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div>
                <button>Play</button>
            </div>
        </>
    );
};

export default Home;