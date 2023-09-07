interface MessageSectionTitleProps {
  title: string
}

export default function MessageSectionTitle({ title }: MessageSectionTitleProps) {
  return (
    <div className='flex col-start-1 col-end-13 justify-center my-5'>
      <div className='w-[200px] bg-gray-200 text-center rounded-lg'>{title}</div>
    </div>
  )
}
