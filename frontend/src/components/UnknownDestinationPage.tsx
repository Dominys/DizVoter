import {ReactElement} from "react";
import {Link} from "react-router-dom";

const UnknownDestinationPage = (): ReactElement => {
    return (
        <div>
            <h2>Nothing to see here!</h2>
            <p>
                <Link to="/" style={{ color: '#FFF' }}>Go to the home page</Link>
            </p>
        </div>
    );
}

export default UnknownDestinationPage;