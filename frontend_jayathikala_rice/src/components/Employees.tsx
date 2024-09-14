import PrintPaySheet from "./PrintPaySheet"



const Employees = () => {
    return (
        <>
            <div className="ml-1">
                
                {/* search field */}
                <div>
                    <input 
                        type="text" 
                        placeholder="Enter the name" 
                        className="border-2 border-gray-800 h-11 pl-2 ml-5 w-1/2 text-gray-900"
                    />
                    <button className="bg-sky-500 border-2 border-sky-500 h-11 px-4">
                        Search
                    </button>
                </div>

                {/* table headers */}
                <div className="bg-gray-500 flex flex-row mt-3 h-10 items-center">
                    {/* id */}
                    <div className="border-2 border-black w-1/12 h-10  flex justify-center content-center">
                        <span className="content-center">ID</span>
                    </div>
                    {/* name */}
                    <div className="border-2 border-black w-5/12 h-10 flex justify-center content-center">
                        <span className="content-center">Employee Name</span>
                    </div>
                    {/* paid status */}
                    <div className="border-2 border-black w-2/12 h-10 flex justify-center content-center">
                        <span className="content-center">Payment Status</span>
                    </div>
                    {/* printer */}
                    <div className="border-2 border-black w-2/12 h-10  flex justify-center content-center">
                        <span className="content-center">Print Paysheet</span>
                    </div>
                    {/* options */}
                    <div className="border-2 border-black w-2/12 h-10 flex justify-center content-center">
                        <span className="content-center">Calculate Salary</span>
                    </div>
                </div>

                {/* data rows */}
                <div className="bg-gray-500 flex flex-row mt-[1px] h-10 items-center">
                    {/* id */}
                    <div className="border-2 border-black w-1/12 h-10  flex justify-center content-center">
                        <span className="content-center">ID</span>
                    </div>
                    {/* name */}
                    <div className="border-2 border-black w-5/12 h-10 flex justify-center content-center">
                        <span className="content-center">Employee Name</span>
                    </div>
                    {/* paid status */}
                    <div className="border-2 border-black w-2/12 h-10 flex justify-center content-center">
                        <span className="content-center">Payment Status</span>
                    </div>
                    {/* printer */}
                    <div className="border-2 border-black w-2/12 h-10 flex justify-center content-center">
                        <PrintPaySheet />
                    </div>
                    {/* options */}
                    <div className="border-2 border-black w-2/12 h-10 flex justify-center content-center">
                        <span className="content-center">Calculate Salary</span>
                    </div>
                </div>



            </div>
        </>
    )
}
export default Employees