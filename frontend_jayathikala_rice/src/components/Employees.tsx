"use client"

import { useEffect, useState } from "react"
import PrintPaySheet from "./PrintPaySheet"
import { useToast } from "@/hooks/use-toast"
import LoadingAnimation from "./LoadingAnimation"
import { CircleX } from "lucide-react"
import EmployeeOptions from "./EmployeeOptions"


const Employees = () => {

    {/* https://ui.shadcn.com/docs/components/toast */ }
    const { toast } = useToast()

    interface dataDataType {
        name: string
        position: string
        monthlySalary: number
        otherAllowances: number
        advance_payments: number
        loan_to_pay: number
        loan_payment_for_month: number
        worked_days_count: number
        should_work_dates_total: number
        calculated_salary: number
        id: number
        etf: number
        specialSupports: number
        other_deductions: number
    }

    const [data, setData] = useState<dataDataType[]>([]);

    const [searchName, setSearchName] = useState("");

    // const [childMessages, setChildMessages] = useState('');

    //this handle by child component
    // const handleChildDataChange = (newChildData: string) => {
    //     setChildMessages(newChildData);
    //     console.log("child is calling")
    // };

    // Create a filtered version of the data
    const filteredData = data.filter(employee =>
        employee.name.toLowerCase().includes(searchName.toLowerCase())
    );

    // fetch data
    const fetchAllEmployees = async () => {

        try {
            console.log("request sending to nextjs API : fetchAllEmployees");

            const res = await fetch('http://localhost:3000/api/fetch-all-employees', {
                cache: 'no-store'
            });

            const responseData = await res.json()
            console.log(responseData)

            if (res.ok) {
                setData(responseData);
                console.log("Success!!!")
            }
            else {
                toast({
                    variant: "destructive",
                    title: "Oh! Something went wrong!",
                    description: "There was a problem with your request. Please Check the internet Connection",
                })

            }
        }
        catch (error) {
            console.error('Error submitting form:', error);
            toast({
                variant: "destructive",
                title: "Oh! Something Unexpected Happend.",
                description: "There was a problem with your request. Please Check the internet Connection",
            })
        } finally {

        }



    };

    useEffect(() => {
        fetchAllEmployees()
    }, []);

    return (
        <>
            <div className="ml-1">

                {/* search field */}
                <div className="flex flex-row">
                    <input
                        type="text"
                        placeholder="Enter the name"
                        value={searchName}
                        onChange={((e) => setSearchName(e.target.value))}
                        className="border-2 border-gray-800 h-11 pl-2 ml-5 w-1/2 text-gray-900"
                    />
                    <button
                        onClick={() => setSearchName("")}
                        className="bg-sky-500 border-2 border-sky-500 h-11 px-4 hover:bg-red-400 hover:border-red-400"
                    >
                        <CircleX />
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
                        <span className="content-center">Position</span>
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

                {filteredData.length > 0 ? (
                    <div>
                        {filteredData.map((d, index) => (
                            <div key={index} className="bg-gray-200 hover:bg-gray-300 flex flex-row mt-[1px] h-10 items-center">
                                {/* id */}
                                <div className="border-2 border-gray-400 w-1/12 h-10  flex justify-center content-center">
                                    <span className="content-center">{d.id}</span>
                                </div>
                                {/* name */}
                                <div className="border-2 border-gray-400 w-5/12 h-10 pl-2 flex justify-items-start content-center">
                                    <span className="content-center">{d.name}</span>
                                </div>
                                {/* paid status */}
                                <div className="border-2 border-gray-400 w-2/12 h-10 flex justify-center content-center">
                                    <span className="content-center">{d.position}</span>
                                </div>
                                {/* printer */}
                                <div className="border-2 border-gray-400 w-2/12 h-10 flex justify-center content-center">
                                    <PrintPaySheet />
                                </div>
                                {/* options */}
                                <div className="border-2 border-gray-400 w-2/12 h-10 flex justify-center content-center">
                                    <EmployeeOptions 
                                        parentData={d}
                                    />
                                </div>
                            </div>
                        ))}
                    </div>
                ) : (
                    <div className="w-full py-10 h-80 flex items-center justify-center">
                        {searchName ? "No matching employees found" : <LoadingAnimation />}
                    </div>)}







            </div >
        </>
    )
}
export default Employees