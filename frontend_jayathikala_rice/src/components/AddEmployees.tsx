

const AddEmployees = () => {
    return (
        <>
            <div className="bg-gray-900">
                <div className="min-h-screen bg-gray-100 flex items-center justify-center text-black">
                    <form className="bg-white px-8 py-2 rounded-lg shadow-md w-full max-w-md">
                        <h2 className="text-2xl font-bold mb-2 text-center text-gray-800">Employee Data</h2>

                        <div className="mb-2">
                            <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-1">Name</label>
                            <input
                                type="text"
                                id="name"
                                name="name"
                                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-sky-500"
                                required
                            />
                        </div>

                        <div className="mb-2">
                            <label htmlFor="position" className="block text-sm font-medium text-gray-700 mb-1">Position</label>
                            <input
                                type="text"
                                id="position"
                                name="position"
                                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-sky-500"
                                required
                            />
                        </div>

                        <div className="mb-2">
                            <label htmlFor="monthly_Salary" className="block text-sm font-medium text-gray-700 mb-1">Monthly Salary</label>
                            <input
                                type="number"
                                id="monthly_Salary"
                                name="monthly_Salary"
                                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-sky-500"
                                required
                            />
                        </div>

                        <div className="mb-2">
                            <label htmlFor="ETF" className="block text-sm font-medium text-gray-700 mb-1">ETF</label>
                            <input
                                type="number"
                                id="ETF"
                                name="ETF"
                                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-sky-500"
                                required
                            />
                        </div>

                        <div className="mb-2">
                            <label htmlFor="bonus" className="block text-sm font-medium text-gray-700 mb-1">Bonus</label>
                            <input
                                type="number"
                                id="bonus"
                                name="bonus"
                                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-sky-500"
                                required
                            />
                        </div>

                        <button
                            type="submit"
                            className="w-full bg-sky-500 text-white py-2 px-4 mb-2 rounded-md hover:bg-sky-600 focus:outline-none focus:ring-2 focus:ring-sky-500 focus:ring-offset-2 transition-colors"
                        >
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        </>
    )
}
export default AddEmployees






