import axios from "axios"
import { useState, useEffect } from "react"
import { useSelector, useStore } from "react-redux"


export const useFetch = (url, method = "GET") => {
    const [data, setData] = useState(null)
    const [isPending, setIsPending] = useState(false)
    const [error, setError] = useState(null)
    const [options, setOptions] = useState(null)

    const token = useSelector((state) => state.token.token)

    const postData = (postData) => {
        setOptions({
            method: "POST",
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(postData)
        })
    }


    useEffect(() => {
        const controller = new AbortController()

        const fetchData = async (fetchOptions) => {
            setIsPending(true)

            try {
                //..fetchOptions are the method, headers, body we made in the postData function
                const res = await fetch(url, { ...fetchOptions, signal: controller.signal })
                if (!res.ok) {
                    throw new Error(res.statusText)
                }
                const data = await res.json()

                setIsPending(false)
                setData(data)
                setError(null)
            } catch (err) {
                if (err.name === "AbortError") {
                    console.log("the fetch was aborted")
                } else {
                    setIsPending(false)
                    setError('Could not fetch the data')
                }
            }
        }
        if (method == 'GET') {
            fetchData()
        }

        if (method == 'POST' && options) {
            fetchData(options)
        }

        return () => {
            controller.abort()
        }

    }, [url, options, method])

    return { data, isPending, error, postData }
}

