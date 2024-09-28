"use client"

import { Button } from "@/components/ui/button"
import {
    Dialog,
    DialogClose,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog"
import { useToast } from "@/hooks/use-toast"
import { useEffect, useState } from "react"


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

interface ChildProps {
    parentData: dataDataType;
}

const DeleteEmployee: React.FC<ChildProps> = ({ parentData, }) => {

    {/* https://ui.shadcn.com/docs/components/toast */ }
    const { toast } = useToast()

    const [id, setId] = useState(0);

    // data fetching handle by this function
    const handleEmployeeDelete = async (empId: number) => {

        console.log("sending keyword to Next.js delete-employee API");

        const res = await fetch(
            "http://localhost:3000/api/delete-employee",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({empId}),
            }
        );

        if (res.ok) {
            toast({
                description: "Employee Updated Successfully",
            })
            console.log("Success!!!")
        } else {
            toast({
                variant: "destructive",
                title: "Oh! Something went wrong!",
                description: "There was a problem with your request. Please Check the internet Connection",
            })
        }
    };


    useEffect(() => {

        setId(parentData.id)

    }, []);


    return (
        <>
            <div className="bg-white w-full">
                <Dialog>
                    <DialogTrigger asChild>
                        <Button type="submit" size="lg" className="w-full bg-red-400 hover:bg-red-700">
                            <span className="">Delete Employee</span>
                        </Button>
                    </DialogTrigger>
                    <DialogContent className="bg-white">
                        <DialogHeader>
                            <DialogTitle>DELETE The Employee</DialogTitle>
                            <DialogDescription>
                                Are you sure to Delete this Employee? This Action CAN NOT be undone!
                            </DialogDescription>
                        </DialogHeader>

                        <DialogFooter className="justify-">

                            {/* when form is submitted this way, it reload the page. 
                            i use that to reload page when a employee is deleted */}
                            <form action="">
                                <Button
                                    type="submit"
                                    size="lg"
                                    onClick={() => handleEmployeeDelete(id)}
                                    className="w-full bg-red-400 hover:bg-red-700"
                                >
                                    <span className="">Delete Employee</span>
                                </Button>
                            </form>



                            <DialogClose asChild>
                                <Button type="button" variant="secondary" className="w-2/3 bg-gray-300 hover:bg-gray-400">
                                    Close
                                </Button>
                            </DialogClose>
                        </DialogFooter>
                    </DialogContent>
                </Dialog>

            </div>
        </>
    )
}
export default DeleteEmployee