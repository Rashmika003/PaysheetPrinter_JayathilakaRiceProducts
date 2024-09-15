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


const DeleteEmployee = () => {
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
                                Are you sure to Delete this Employee
                            </DialogDescription>
                        </DialogHeader>

                        <DialogFooter className="justify-">

                            <Button type="submit" size="lg" className="w-1/3 bg-red-400 hover:bg-red-700">
                                <span className="">Delete Employee</span>
                            </Button>

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